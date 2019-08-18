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
</head>
<body>
<h1>Your plans</h1>
<table>
    <tr>
        <th>Plan's name</th>
        <th>Start date</th>
        <th>Coach login</th>
    </tr>
    <c:forEach var="plan" items="${plans}">
        <tr>
            <td>${plan.name}</td>
            <td>${plan.startDay}</td>
            <td>${plan.coach.login}</td>
            <td>
                <a href="/plan/${plan.id}/athlete/allTrainingsByWeeks/">Show trainings</a>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
