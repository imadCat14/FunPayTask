<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    <title><fmt:message key="label.title.review"/></title>
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/star.css" rel="stylesheet">
</head>
<body>
<%@ include file="menuUser.jsp" %>
<h3>${server.chronicle.chronicleName}<br>
    ${server.serverName}
    ${server.averageMark}
</h3>
<form action="controller" method="POST">
    <input type="hidden" name="command" value="rate-server"/>
    <input type="hidden" value="${user.login}" name="login"/>
    <input type="hidden" value="${server.serverId}" name="serverId"/>
    <input type="hidden" value="${server.serverName}" name="serverName"/>
    <div class="star-rating">
        <fieldset>
            <input onchange="form.submit()" type="radio" id="star5" name="rating"
                   value="5"/><label for="star5" title="Outstanding">5</label>
            <input onchange="form.submit()" type="radio" id="star4" name="rating"
                   value="4"/><label for="star4" title="Very Good">4</label>
            <input onchange="form.submit()" type="radio" id="star3" name="rating" value="3"/><label
                for="star3" title="Good">3</label>
            <input onchange="form.submit()" type="radio" id="star2" name="rating" value="2"/><label
                for="star2" title="Poor">2</label>
            <input onchange="form.submit()" type="radio" id="star1" name="rating" value="1"/><label
                for="star1" title="Very Poor">1</label>
        </fieldset>
    </div>
</form>
<table class="table table-hover table-sm" id="myTable">
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.usersLogin"/></th>
        <th><fmt:message key="label.review"/></th>
        <th><fmt:message key="label.data"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="reviews" items="${reviews}">
        <td><c:out value=" ${reviews.login}"></c:out></td>
        <td><c:out value=" ${reviews.reviewText}"></c:out></td>
        <td><c:out value=" ${reviews.reviewDate}"></c:out></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<table class="table table-hover table-sm">
    <tbody>
    <tr>
        <form action="controller">
            <td>
                <input type="hidden" name="command" value="add-review"/>
                <input type="hidden" value="${user.login}" name="login"/>
                <input type="hidden" value="${server.serverName}" name="serverName"/>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="textReview" cols="70"
                          placeholder="Enter review"></textarea>
            </td>
            <td>
                <input type="hidden" value="${server.serverId}" name="serverId"/>
                <input class="btn btn-outline-success" type="submit" id="submit" value="add review">
            </td>
        </form>
    </tr>
    </tbody>
</table>
<%@ include file="/jsp/footer.jsp" %>
</br>
</body>
</html>