<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Reviews</title>
</head>
<body><h3>Welcome</h3>
<hr/>
<input type="hidden" name="userID" value=${user.userID}/>
${user.login}, ${user.userRole}, hello!
<hr/>
<p>Servers page</p>

${userName},  hello!



<table>
    <form action="controller">
        <input type="hidden" name="command" value="add-server"/>

        <tr>
            <th>server name</th>
            <th>server description</th>

        </tr>
        <c:forEach var="servers" items="${servers}">

            <td><c:out value=" ${servers.serverName}"></c:out></td>
            <td><c:out value=" ${servers.serverDescription}"></c:out></td>

            </tr>
        </c:forEach>


        <%--<form action="controller">--%>
        <%--<input type="hidden" name="command" value="add-review"/>--%>
        <div>
            <label>Введите описание</label><br>
            <input type="text" name="serverDescription" placeholder="Enter description"><br>
            <input type="hidden" value="${server}" name="serverId"/>
            <input type="submit" id="submit" value="add server">
        </div>


    </form>
</table>
<a href="controller?command=logout">Logout</a>
</body>
</html>
