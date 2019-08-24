<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script>
        function confirmInvitation(athleteId, coachId, coachLogin) {
            if (confirm("Would you like to confirm invitation from login: \"" + coachLogin + "\"")) {
                window.location.href = "/athlete/acceptInvitation/" + coachId;
            }
        }

        function confirmRejectInvitation(athleteId, coachId, coachLogin) {
            if (confirm("Would you like to reject invitation from login: \"" + coachLogin + "\"")) {
                window.location.href = "/athlete/rejectInvitation/" + coachId;
            }
        }

        function confirmRemove(athleteId, coachId, coachLogin) {
            if (confirm("Would you like to remove coach with login: \"" + coachLogin + "\"")) {
                window.location.href = "/athlete/removeCoach/" + coachId;
            }
        }
    </script>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerAthlete.jspf" %>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
    <br>
    <p class="table-head">Potential coaches</p>

<table class="container">
    <tr>
        <th>Login</th>
        <th>Action</th>

    </tr>
    <c:forEach var="potentialCoache" items="${potentialCoaches}">
        <tr>
            <td>${potentialCoache.login}</td>
            <td>
                <a class="day-link" href="#" onclick="confirmInvitation(${athleteId}, '${potentialCoache.id}', '${potentialCoache.login}')">Confirm invitation</a>
                <a class="day-link" href="#" onclick="confirmRejectInvitation(${athleteId}, '${potentialCoache.id}', '${potentialCoache.login}')">Reject invitation</a>
            </td>
        </tr>

    </c:forEach>
</table>
    <p class="table-head">Coaches list</p>

<table class="container">
    <tr>
        <th>Login</th>
        <th>Action</th>

    </tr>
    <c:forEach var="coache" items="${coaches}">
        <tr>
            <td>${coache.login}</td>
            <td>
                <a class="day-link" href="#" onclick="confirmRemove(${athleteId}, '${coache.id}', '${coache.login}')">Remove from your coaches</a>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
