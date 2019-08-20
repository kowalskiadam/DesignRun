<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 02.08.2019
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function confirmCancelInvitation(coachId, athleteId, login) {
            if (confirm("Would you like to cancel invitation for login: \"" + login + "\"")) {
                window.location.href = "/coach/cancelInvitation/" + athleteId;
            }
        }

        function confirmRemove(coachId, athleteId, login) {
            if (confirm("Would you like to remove athlete with login: \"" + login + "\"")) {
                window.location.href = "/coach/remove/" + athleteId;
            }
        }
    </script>
</head>
<body>
<h1>Manage athletes</h1>

<a href="/coach/newAthlete"><h3>Create new athlete</h3></a>

<h3>Find athlete</h3>
<form method="post">
    Login to find: <input type="text" name="loginToFind"><br>
    <input type="submit" value="Submit">
</form>
<h3>Request list</h3>
<table>
    <tr>
        <th>Login</th>
        <th>FirstName</th>
        <th>LastName</th>
    </tr>
    <c:forEach var="potentialAthlete" items="${potentialAthletes}">
        <tr>
            <td>${potentialAthlete.login}</td>
            <td>${potentialAthlete.firstName}</td>
            <td>${potentialAthlete.lastName}</td>
            <td>
                <a href="#" onclick="confirmCancelInvitation(${coachId}, '${potentialAthlete.id}', '${potentialAthlete.login}')">Cancel invitation</a>
            </td>
        </tr>

    </c:forEach>
</table>

<h3>Athletes list</h3>
<table>
    <tr>
        <th>Login</th>
        <th>FirstName</th>
        <th>LastName</th>
    </tr>
    <c:forEach var="athlete" items="${athletes}">
        <tr>
            <td>${athlete.login}</td>
            <td>${athlete.firstName}</td>
            <td>${athlete.lastName}</td>
            <td>
                <a href="#" onclick="confirmRemove(${coachId}, '${athlete.id}', '${athlete.login}')">Remove from your athletes</a>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
