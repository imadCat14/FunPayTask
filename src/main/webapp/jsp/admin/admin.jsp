<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link href="css/footer.css" rel="stylesheet">
</head>
<html>
<%@ include file="menuAdmin.jsp" %>
<br><br><br><br><br>
<div><h3><fmt:message key="label.tableServerName"/></h3></div>
<table class="table table-hover table-sm" id="myTable">

    <caption><h3><fmt:message key="label.tableServerName"/></h3></caption>
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.id"/></th>
        <th><fmt:message key="label.serverName"/></th>
        <th><fmt:message key="label.serverDescription"/></th>
        <th><fmt:message key="label.serverChronicle"/></th>
        <th><fmt:message key="label.serverAverageMark"/></th>
    </tr>
   </thead>
    <c:forEach var="servers" items="${servers}">

        <td><c:out value=" ${servers.serverId}"/></td>
        <td><c:out value=" ${servers.serverName}"/></td>
        <td><c:out value=" ${servers.description}"/></td>
        <td><c:out value=" ${servers.chronicle.chronicleName}"/></td>
        <td><c:out value=" ${servers.averageMark}"/></td>

        <td>
            <div class="input-group mb-3">
          <textarea class="form-control mr-sm-2" type="text" name="newDescription" cols="10" rows="1"
                    required></textarea>
                <div class="input-group-append">
                    <input type="hidden" name="command" value="update-server"/>
                    <input type="hidden" value="${servers.serverName}" name="serverName"/>
                    <input class="btn btn-outline-success btn-sm" value="update" type="submit" id="submit">
                </div></div>

            </form>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>