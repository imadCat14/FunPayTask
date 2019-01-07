<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link href="css/footer.css" rel="stylesheet">
</head>
<html>
<%@ include file="menuAdmin.jsp" %>
<div style="width: 50%" class="form-group">
    <form class="login-form" name="loginForm" method="POST" action="controller">
        <h3><fmt:message key="label.server.fillForm"/></h3>
        <input type="hidden" name="command" value="add-server"/>

        <%--<h5>Upload File:</h5>--%>
        <%--<br/>--%>
        <%--<INPUT type="file" name="content">--%>
        <%--<br/><br/>--%>


        <label for="formServerInput"> <fmt:message key="label.serverName"/> </label>
        <input type="text" class="form-control" id="formServerInput" required
               name="serverName" value=""/>
        <label for="formServerInput"> <fmt:message key="label.serverChronicle"/> </label>
        <input type="text" class="form-control" id="formServerInput"
               name="serverChronicle" required value=""/>
        <div class="form-group">
            <label for="descriptionServerInput"><fmt:message key="label.serverDescription"/></label>
            <textarea class="form-control" id="descriptionServerInput" rows="3" name="serverDescription" pattern></textarea>
        </div>
        <fmt:message key="label.submit.send" var="buttonValue"/>
        <input class="btn btn-outline-success btn-sm" type="submit" id="submit" value="${buttonValue}">
        <c:if test="${not empty infoData}">
        <div class="alert-success" align="centre">
                ${infoData}
        </div>
        </c:if>
</div>
</form>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>
