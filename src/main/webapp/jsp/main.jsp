<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="css/index.css" rel="stylesheet">
<link href="css/table.css" rel="stylesheet">
<html>
<head><title>Welcome</title>
</head>
<body>
<%@ include file="menuUser.jsp" %>
<h3>Welcome</h3>
<hr/>
<input type="hidden" name="userID" value=${user.userID}/>

${user.login}, ${user.userRole}, hello!
<hr/>


<table class="table_dark">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>description</th>
        <th>reviews</th>
    </tr>
    <c:forEach var="servers" items="${servers}">

            <td><c:out value=" ${servers.serverId}"></c:out></td>
            <td><c:out value=" ${servers.serverName}"></c:out></td>
            <td><c:out value=" ${servers.description}"></c:out></td>

            <td>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="see-reviews"/>
                <input type="hidden" value="${servers.serverId}" name="serverId"/>
                <input value="Просмотреть" type="submit" >
            </form>
            </td>>
            <br>
        </tr>
    </c:forEach>
</table>
<a href="controller?command=logout">Logout</a>
</body>
</html>