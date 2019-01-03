<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link href="css/menu.css" rel="stylesheet">
<nav role='navigation'>
	<br>
	Login: ${user.login}, <br>${user.userRole}
	<ul>
		<li><a href="#"><fmt:message key="label.menu.servers"/></a></li>
		<li><a href="controller?command=logout"><fmt:message key="label.menu.logout"/></a></li>
	</ul>
</nav>
			