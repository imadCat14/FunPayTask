<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">--%>

    <link href="css/footer.css" rel="stylesheet">
</head>
<body>
<%@ include file="menuUser.jsp" %>
<input type="hidden" name="userID" value=${user.userID}/>

<table class="table table-hover table-sm" id="resultTable">
    <caption><h3><fmt:message key="label.tableServerName"/></h3></caption>
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.id"/></th>
        <th><fmt:message key="label.serverName"/></th>
        <th><fmt:message key="label.serverDescription"/></th>
        <th><fmt:message key="label.serverChronicle"/></th>
        <th><fmt:message key="label.serverAverageMark"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="servers" items="${servers}">

        <td><c:out value=" ${servers.serverId}"/></td>
        <td><c:out value=" ${servers.serverName}"/></td>
        <td><c:out value=" ${servers.description}"/></td>
        <td><c:out value=" ${servers.chronicle.chronicleName}"/></td>
        <td><c:out value=" ${servers.averageMark}"/></td>

        <td>

        </td>
        <td>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="see-reviews"/>
                <input type="hidden" value="${servers.serverId}" name="serverIdId"/>
                <input type="hidden" value="${servers.serverName}" name="serverName"/>
                <fmt:message key="label.submit.seeReviews" var="buttonValue"/>
                <input class="btn btn-outline-success btn-sm" type="submit" id="submit" value="${buttonValue}">
            </form>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>