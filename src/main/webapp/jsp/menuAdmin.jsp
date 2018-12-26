<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="jsp" prefix="label.">
<link href="css/menu.css" rel="stylesheet">
<nav role='navigation'>
    <br>
    Login: ${user.login}, <br>${user.userRole}
    <ul>
        <li></li>

        <li><a href="#">Servers</a>
            <ul>
                <li><a href="">Our team</a></li>
                <li><a href="">History</a></li>
            </ul>
        </li>
        <li><a href="#"><fmt:message key="users"/></a></li>
        <li><a href="controller?command=logout">Logout</a></li>

    </ul>
</nav>
</fmt:bundle>