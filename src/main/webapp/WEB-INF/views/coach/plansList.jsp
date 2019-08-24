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
<p class="table-head">Plan list</p>
<table class="container">
    <tr>
        <th>Name</th>
        <th>StartDay</th>
        <th>Number of weeks</th>
        <th>Athlete</th>
        <th>Method</th>
        <th>Details</th>
    </tr>
    <c:forEach var="plan" items="${coachPlans}">
        <tr>
            <td>${plan.name}</td>
            <td>${plan.startDay}</td>
            <td>${plan.weeksNumber}</td>
            <td>${plan.athlete.login}</td>
            <td>${plan.method.name}</td>
            <td ><a class="training-link" href="/plan/${plan.id}/coach/details/">Details</a></td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
