<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 03.08.2019
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function confirmInvitation(athleteId, coachId, coachLogin) {
            if (confirm("Would you like to confirm invitation from login: \"" + coachLogin + "\"")) {
                window.location.href = "/athlete/" + athleteId + "/acceptInvitation/" + coachId;
            }
        }

        function confirmRejectInvitation(athleteId, coachId, coachLogin) {
            if (confirm("Would you like to reject invitation from login: \"" + coachLogin + "\"")) {
                window.location.href = "/athlete/" + athleteId + "/rejectInvitation/" + coachId;
            }
        }

        function confirmRemove(athleteId, coachId, coachLogin) {
            if (confirm("Would you like to remove coach with login: \"" + coachLogin + "\"")) {
                window.location.href = "/athlete/" + athleteId + "/removeCoach/" + coachId;
            }
        }
    </script>
</head>
<body>
<h1>Your couches</h1>
<h3>Potential coaches</h3>
<table>
    <tr>
        <th>Login</th>
    </tr>
    <c:forEach var="potentialCoache" items="${potentialCoaches}">
        <tr>
            <td>${potentialCoache.login}</td>
            <td>
                <a href="#" onclick="confirmInvitation(${athleteId}, '${potentialCoache.id}', '${potentialCoache.login}')">Confirm invitation</a>
                <a href="#" onclick="confirmRejectInvitation(${athleteId}, '${potentialCoache.id}', '${potentialCoache.login}')">Reject invitation</a>
            </td>
        </tr>

    </c:forEach>
</table>

<h3>Coaches list</h3>
<table>
    <tr>
        <th>Login</th>
    </tr>
    <c:forEach var="coache" items="${coaches}">
        <tr>
            <td>${coache.login}</td>
            <td>
                <a href="#" onclick="confirmRemove(${athleteId}, '${coache.id}', '${coache.login}')">Remove from your coaches</a>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
