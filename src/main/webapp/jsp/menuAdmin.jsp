<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link href="css/menu.css" rel="stylesheet">
<nav role='navigation'>
    <br>
    Login: ${user.login}, <br>${user.userRole}
    <ul>

        <li><a href="#"><fmt:message key="label.menu.servers"/></a>
            <ul>
                <li><a href="controller?command=show-servers"><fmt:message key="label.menu.showServers"/></a></li>
                <li><a href="controller?command=fill-new-server-data"><fmt:message key="label.menu.addServer"/></a></li>
            </ul>
        </li>
        <li><a href="controller?command=show-users"><fmt:message key="label.users"/></a></li>
        <li><a href="controller?command=logout"><fmt:message key="label.menu.logout"/></a></li>
    </ul>
</nav>