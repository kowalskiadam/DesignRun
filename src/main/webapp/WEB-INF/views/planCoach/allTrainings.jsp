<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
    <br>
    <p class="table-head">All trainings</p>
<table class="container">
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
