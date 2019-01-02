<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="jsp"/>
<html>
<head>
    <link href="css/index.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
</head>
<body>
<h3>WRRONG COMMAND</h3>

<form name="goToMainPage" method="post" action="controller">
    <input type="hidden" name="command" value="go-to-main-page">
    <button type="submit" class="btn btn-danger">Go to main page</button>
</form>
</body>
</html>