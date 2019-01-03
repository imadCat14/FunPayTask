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

<h2><fmt:message key="label.server.fillForm"/></h2>
<br><br><br><br><br>

<div class="login-page">
    <div class="form">
        <form class="login-form" name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="add-alien"/>
            <input type="text" placeholder=<fmt:message key="label.serverName"/> name="alienName" value=""/>
            <input type="text" placeholder=<fmt:message key="label.serverChronicle"/> name="serverChronicle" value=""/>
            <input type="text" placeholder=<fmt:message key="label.serverDescription"/> name="serverDescription" value=""/>
            <fmt:message key="label.submit.send" var="buttonValue"/>
            <input type="submit" id="submit" value="${buttonValue}">
            <%--<p class="message"><fmt:message key="label.notregistrated"/> <a--%>
            <%--href="controller?command=goToRegistrationPage"><fmt:message key="label.registration"/></a></p>--%>
            <%--<p align="center" class="message"> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} </p>--%>
            <%--</form>--%>
            <c:if test="${not empty wrongInfoData}">
            <div class="alert-danger" align="centre">
                    ${wrongInfoData}
            </div>
            </c:if>
    </div>

</div>
</form>

</body>
</html>
