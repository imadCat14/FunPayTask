<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link href="css/footer.css" rel="stylesheet">
</head>
<body>

<%@ include file="menuAdmin.jsp" %>
<div><h2><fmt:message key="label.tableUserReviews"/></h2></div>

<table class="table table-hover table-sm" id="myTable">
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.usersLogin"/></th>
        <th><fmt:message key="label.serverName"/></th>
        <th><fmt:message key="label.review"/></th>
        <th><fmt:message key="label.data"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="reviews" items="${reviews}">
        <td><c:out value=" ${reviews.login}"></c:out></td>
        <td><c:out value=" ${reviews.serverName}"></c:out></td>
        <td><c:out value=" ${reviews.reviewText}"></c:out></td>
        <td><c:out value=" ${reviews.reviewDate}"></c:out></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="/jsp/footer.jsp" %>
</body>
