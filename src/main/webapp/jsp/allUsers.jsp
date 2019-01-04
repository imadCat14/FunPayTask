<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>

<head>
    <link href="css/index.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
</head>
<html>
<%@ include file="menuAdmin.jsp" %>
<br><br><br><br><br>

<table>
    <caption><h1><fmt:message key="label.tableUserName"/></h1></caption>
    <tr>
        <th><fmt:message key="label.userLogin"/></th>
        <th><fmt:message key="label.userPassword"/></th>
        <th><fmt:message key="label.userEmail"/></th>
        <th><fmt:message key="label.userStatus"/></th>

    </tr>
    <c:forEach var="users" items="${users}">
        <td><c:out value=" ${users.login}"></c:out></td>
        <td><c:out value=" ${users.password}"></c:out></td>
        <td><c:out value=" ${users.email}"></c:out></td>
        <td><c:out value=" ${users.userStatus}"></c:out></td>
        <td><form action="controller" method="POST">
            <input type="hidden" name="command" value="change-user-status"/>
            <input type="hidden" name="selectedUser" value="${users.login}"/>
            <select name="selectedStatus"onchange="this.form.submit()">
                <option value="">change status</option>
                <option value="0">Blocked</option>
                <option value="1">Active</option>
            </select>
        </form></td>
        <%--<td><c:out value=" ${aliens.homeland.homelandId}"></c:out></td>--%>
        <%--<td><c:out value=" ${aliens.homeland.homelandName}"></c:out></td>--%>
        <%--<td><c:out value=" ${aliens.averageMark}"></c:out></td>--%>
        <%--<td>--%>
        <%--<form method="POST" action="controller">--%>
        <%--<input type="hidden" name="command" value="dell-alien"/>--%>
        <%--<input type="hidden" value="${aliens.alienId}" name="alienId"/>--%>
        <%--<input value="Delete" type="submit">--%>
        <%--</form>--%>
        <%--</td>--%>

        <br>
        </tr>
    </c:forEach>
</table>

</body>
</html>
