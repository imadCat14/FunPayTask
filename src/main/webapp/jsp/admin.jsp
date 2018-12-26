<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="jsp"/>
    <head>
<link href="css/index.css" rel="stylesheet">
<link href="css/table.css" rel="stylesheet">
    </head>
<html>
<%--<%@ include file="menuAdmin.jsp" %>--%>
<br><br><br><br><br>

<br>
<fmt:bundle basename="jsp" prefix="label.">

<table>
    <tr>
        <th><fmt:message key="id"/>id</th>
        <th>name</th>
        <th>description</th>
        <th>chronicleId</th>
        <th>chronicleName</th>
    </tr>
    <c:forEach var="servers" items="${servers}">

        <td><c:out value=" ${servers.serverId}"></c:out></td>
        <td><c:out value=" ${servers.serverName}"></c:out></td>
        <td><c:out value=" ${servers.description}"></c:out></td>
        <td><c:out value=" ${servers.homeland.homelandId}"></c:out></td>
        <td><c:out value=" ${servers.chronicle.chronicleName}"></c:out></td>

        <td>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="dell-server"/>
                <input type="hidden" value="${servers.serverId}" name="serverId"/>
                <input value="Delete" type="submit" >
            </form>
        </td>>
        <br>
        </tr>
    </c:forEach>
</table>

</body>
</fmt:bundle>
</html>