<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	  integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<nav class="navbar navbar-expand-lg navbar-light sticky-top" style="background-color:#8DC26F;">

	<a class="navbar-brand" href="#">Login: ${user.login}, <br>${user.userRole}</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a href="controller?command=show-servers" class="nav-link">
				<fmt:message key="label.menu.showServers"/></a>
			</li>

			<li class="nav-item"><a href="controller?command=show-users" class="nav-link">
				<fmt:message key="label.users"/></a>
			</li>
			<li class="nav-item"><a href="controller?command=logout" class="nav-link">
				<fmt:message key="label.menu.logout"/></a>
			</li>
		</ul>
		<form class="form-inline my-2 my-lg-0" method="POST" action="controller">
			<input type="hidden" name="command" value="show-server-by-name"/>
			<input class="form-control mr-sm-2" type="text" placeholder=
			<fmt:message key="label.serverName"/> name="serverName" value=""/>
			<fmt:message key="label.submit.searchServer" var="buttonValue"/>
			<input class="btn btn-outline-dark btn-sm" type="submit" value="${buttonValue}">
		</form>
	</div>
</nav>
			