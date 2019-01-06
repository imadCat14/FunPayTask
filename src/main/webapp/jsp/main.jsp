<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link href="css/index.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
</head>
<html>
<%@ include file="menuUser.jsp" %>
<h3>Greetings, traveller!</h3>


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

        <td><c:out value=" ${servers.serverId}"/></td>
        <td><c:out value=" ${servers.serverName}"/></td>
        <td><c:out value=" ${servers.description}"/></td>
        <td><c:out value=" ${servers.chronicle.chronicleName}"/></td>
        <td><c:out value=" ${servers.averageMark}"/></td>

        <td>


        <td>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="see-reviews"/>
                <input type="hidden" value="${servers.serverId}" name="serverId"/>
                <input value="Watch reviews" type="submit">
            </form>
        </td>
        >
        <br>
        </tr>
    </c:forEach>
</table>
</body>
</html>