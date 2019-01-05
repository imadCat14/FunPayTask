<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<link href="css/index.css" rel="stylesheet">
<link href="css/table.css" rel="stylesheet">
<html>
<head><title>Greetings, traveller!</title>
</head>
<body>
<%@ include file="menuUser.jsp" %>
<h3>Greetings, traveller!</h3>
<hr/>
<input type="hidden" name="userID" value=${user.userID}/>

${user.login}, ${user.userName}, hello!
<hr/>


<table>
    <caption><h1><fmt:message key="label.tableServerName"/></h1></caption>
    <tr>
        <th><fmt:message key="label.id"/></th>
        <th><fmt:message key="label.serverName"/></th>
        <th><fmt:message key="label.serverDescription"/></th>
        <th><fmt:message key="label.serverChronicle"/></th>
        <th><fmt:message key="label.serverAverageMark"/></th>
    </tr>
    <c:forEach var="servers" items="${servers}">
        <td><c:out value=" ${servers.serverId}"></c:out></td>
        <td><c:out value=" ${servers.serverName}"></c:out></td>
        <td><c:out value=" ${servers.description}"></c:out></td>
        <td><c:out value=" ${servers.chronicle.chronicleName}"></c:out></td>
        <td><c:out value=" ${servers.averageMark}"></c:out></td>


        <td>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="see-reviews"/>
                <input type="hidden" value="${servers.serverId}" name="serverId"/>
                <input value="Просмотреть" type="submit">
            </form>
        </td>
        >
        <br>
        </tr>
    </c:forEach>
</table>
<a href="controller?command=logout">Logout</a>
</body>
</html>