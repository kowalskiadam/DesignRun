<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <script>


        function confirmRemove(coachId, athleteId, login) {
            if (confirm("Would you like to remove athlete with login: \"" + login + "\"")) {
                window.location.href = "/coach/remove/" + athleteId;
            }
        }
    </script>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>

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
