<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 10.08.2019
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Name</th>
        <th>StartDay</th>
        <th>Number of weeks</th>
        <th>Athlete</th>
        <th>Method</th>
        <th>Name</th>
        <th>Name</th>

    </tr>
    <c:forEach var="training" items="${trainings}">
        <tr>
            <td>${training.day.date}</td>
            <td>${training.orderInDay}</td>
            <td>${training.trainingType.name}</td>
            <td>${training.name}</td>
            <td>${training.distance}</td>
            <td>${training.description}</td>
            <td>${training.athleteComment}</td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
