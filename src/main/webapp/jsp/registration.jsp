<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Welcome</title>
    <link href="css/index.css" rel="stylesheet">
</head>
<body>
<div class="login-page">
    <div class="form">
        <h4>Registration</h4>
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="registration"/>
            <input type="text" name="login" pattern="^[a-zA-Z][a-zA-Z0-9-_]{4,20}$" placeholder="login" required value=""/>
            <br/> <input type="password" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}$" placeholder="password" required value=""/>
            <input type="mail" name="mail"  placeholder="mail" required value=""/>
            ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
            <input type="submit" id="submit" value="Send"/>
            <p class="message"><a href="controller?command=logout">Back and log in</a></p>
            <p  align="center" class="message"> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} </p>
        </form>
        <c:if test="${not empty wrongInfoData}">
            <div class="alert-danger" align="centre">
                    ${wrongInfoData}
            </div>
        </c:if>
    </div>
</div>

</body>
</html>