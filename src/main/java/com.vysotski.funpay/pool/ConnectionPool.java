package com.vysotski.funpay.pool;

import com.vysotski.funpay.resource.ResourceManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> workingConnections;
    private static final int DEFAULT_POOL_SIZE = 5;
    private static ConnectionPool instance;
    private static ReentrantLock createLock = new ReentrantLock();
    private static AtomicBoolean isCreate = new AtomicBoolean(false);
    private String driverName;
    private String url;
    private String username;
    private String password;
    private int poolSize;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public ConnectionPool() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        this.driverName = resourceManager.getValue(DBParameter.DRIVER);
        this.url = resourceManager.getValue(DBParameter.URL);
        this.username = resourceManager.getValue(DBParameter.USER);
        this.password = resourceManager.getValue(DBParameter.PASSWORD);
        try {
            this.poolSize = Integer.parseInt(resourceManager.getValue(DBParameter.POOLSIZE));
        } catch (NumberFormatException e) {
            poolSize = DEFAULT_POOL_SIZE;
        }
        try {
            init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreate.get()) {
            try {
                createLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreate.set(true);
                }
            } finally {
                createLock.unlock();
            }
        }
        return instance;
    }


    public void init() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            workingConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            }
            logger.log(Level.INFO, "Success pool init");
        } catch (SQLException e) {
            throw new ConnectionPoolException("Sql exception in connectionPool", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.ERROR, "Drivers not found");
            throw new ConnectionPoolException("Cant find database driver class", e);
        }
    }

    public ProxyConnection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            workingConnections.put(connection);
            logger.log(Level.INFO, "Success take pool connection");
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source", e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            workingConnections.remove(connection);
            freeConnections.put(connection);
            logger.log(Level.INFO, "Success pool release connection");
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    private void clearConnectionQueue() throws SQLException {
        Connection connection;
        while ((connection = freeConnections.poll()) != null) {
            connection.close();
        }
    }

    public void closePool()  {
        if (instance != null) {
            try {
                instance.clearConnectionQueue();
                instance = null;
                logger.log(Level.INFO, "Success pool close");
                java.sql.Driver sqlDriver = DriverManager.getDriver(DBParameter.URL);
                DriverManager.deregisterDriver(sqlDriver);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }

    }

    public Object clone() throws CloneNotSupportedException{
        if(instance !=null){
            throw new CloneNotSupportedException();
        }
        return super.clone();
    }

}
