<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head><title><fmt:message key="label.registration"/></title>
    <link href="css/index.css" rel="stylesheet">
    <link href="css/menu.css" rel="stylesheet">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
</head>
<body>
<%--<%@ include file="jsp/header.jsp" %>--%>
<div class="login-page">
    <div class="form">
        <h4><fmt:message key="label.registration"/></h4>
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="registration"/>
            <input type="text" name="login" pattern="^[a-zA-Z][a-zA-Z0-9-_]{4,20}$" placeholder=
            <fmt:message key="label.login"/> required value=""/>
            <br/> <input type="password" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}$"
                         placeholder=
                         <fmt:message key="label.password"/> required value=""/>
            <input type="email" name="email" placeholder=
            <fmt:message key="label.email"/> required value=""/>
            ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
            <fmt:message key="label.submit.send" var="buttonValue"/>
            <input type="submit" id="submit" value="${buttonValue}">
            <p class="message"><a href="controller?command=logout"><fmt:message key="label.submit.backToLogin"/></a></p>
            <p align="center" class="message"> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} </p>
        </form>
        <c:if test="${not empty wrongInfoData}">
            <div class="alert-danger" align="centre">
                    ${wrongInfoData}
            </div>
        </c:if>
    </div>
</div>

<div class="star-rating">
    <div class="star-rating__wrap">
        <input class="star-rating__input" id="star-rating-5" type="radio" name="rating" value="5">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5" title="5 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-4" type="radio" name="rating" value="4">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4" title="4 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-3" type="radio" name="rating" value="3">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3" title="3 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-2" type="radio" name="rating" value="2">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2" title="2 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-1" type="radio" name="rating" value="1">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1" title="1 out of 5 stars"></label>
    </div>
</div>
</body>
</html>