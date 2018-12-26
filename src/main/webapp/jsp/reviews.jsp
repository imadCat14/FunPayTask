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
<p>Reviews page</p>

${alien},  hello!



<table>
    <form action="controller">
        <input type="hidden" name="command" value="add-review"/>

<tr>
<th>user login</th>
<th>review</th>
<th>date</th>

</tr>
<c:forEach var="reviews" items="${reviews}">

    <td><c:out value=" ${reviews.userId}"></c:out></td>
    <td><c:out value=" ${reviews.textReview}"></c:out></td>
    <td><c:out value=" ${reviews.dateReview}"></c:out></td>

    </tr>
</c:forEach>


<%--<form action="controller">--%>
        <%--<input type="hidden" name="command" value="add-review"/>--%>
        <div>
            <label>Введите комментарий</label><br>
            <input type="text" name="textReview" placeholder="Enter review"><br>
            <input type="hidden" value="${server}" name="serverId"/>
            <input type="submit" id="submit" value="add review">
        </div>


    </form>
</table>
<a href="controller?command=logout">Logout</a>
</body>
</html>