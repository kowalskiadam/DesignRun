<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    </script>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
    <br>
    <p class="table-head">Athletes request list</p>

<table class="container">
    <tr>
        <th>Login</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Cancel invitation</th>

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







</body>
</html>
