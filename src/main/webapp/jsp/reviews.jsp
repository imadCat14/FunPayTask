<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head><title>Reviews</title>
</head>
<body><h3>Welcome</h3>
<hr/>
<input type="hidden" name="userID" value=${user.userID}/>
${user.login}, ${user.userRole}, hello!
<hr/>
<p>Reviews page</p>

${user.login},  hello!

<table>
    <caption><h1><fmt:message key="label.tableReviewsName"/></h1></caption>
    <form action="controller">
        <input type="hidden" name="command" value="add-review"/>
    <tr>
        <th><fmt:message key="label.id"/></th>
        <th><fmt:message key="label.reviewText"/></th>
        <th><fmt:message key="label.reviewDate"/></th>

    </tr>
<c:forEach var="reviews" items="${reviews}">

    <td><c:out value=" ${reviews.userID}"></c:out></td>
    <td><c:out value=" ${reviews.reviewText}"></c:out></td>
    <td><c:out value=" ${reviews.reviewDate}"></c:out></td>

    </tr>
</c:forEach>


<%--<form action="controller">--%>
        <%--<input type="hidden" name="command" value="add-review"/>--%>
        <div>
            <label>Insert review</label><br>
            <input type="text" name="reviewText" placeholder="Insert review"><br>
            <input type="hidden" value="${server}" name="serverId"/>
            <input type="submit" id="submit" value="add-review">
        </div>


    </form>
</table>
<a href="controller?command=logout">Logout</a>
</body>
</html>