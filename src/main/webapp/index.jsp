<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<%--<fmt:setLocale value="${sessionScope.locale}"/>--%>
<fmt:bundle basename="jsp">

    <html>
    <head>
        <link href="css/index.css" rel="stylesheet">
        <title><fmt:message key="label.title"/></title>

    </head>
    <body>

    <form  method="POST" action="controller">
        <input type="hidden" name="command" value="change-language"/>
        <button type="submit" name="language"
                value="ru_RU" class="btn btn-default navbar-btn">RU</button>
        <button type="submit" name="language"
                value="en_US" class="btn btn-default navbar-btn">EN</button>
    </form>



    <div class="login-page">
        <div class="form">
            <form class="login-form" name="loginForm" method="POST" action="controller">
                <input type="hidden" name="command" value="login"/>
                <input type="text" placeholder="login" name="login" value=""/>
                <input type="password" placeholder="password" name="password" value=""/>
                <fmt:message key="label.submit.login" var="buttonValue"/>
                <input type="submit" id="submit" value="${buttonValue}">
                <p class="message"><fmt:message key="label.notregistrated"/> <a
                        href="controller?command=goToRegistrationPage"><fmt:message key="label.registration"/></a></p>
                <p align="center" class="message"> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} </p>
            </form>
            <c:if test="${not empty wrongInfoData}">
                <div class="alert-danger" align="centre">
                        ${wrongInfoData}
                </div>
            </c:if>
        </div>

    </div>
    </form>
</fmt:bundle>
</body>

</html>
