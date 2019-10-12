<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerAthlete.jspf" %>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
    <br>
    <a href="/athlete/planList">Back</a>

    <br>
<table class="container">
    <tr>
        <th>Week</th>
        <th>Monday</th>
        <th>Tuesday</th>
        <th>Wednesday</th>
        <th>Thursday</th>
        <th>Friday</th>
        <th>Saturday</th>
        <th>Sunday</th>
    </tr>
    <c:forEach var="week" items="${weeks}">
        <tr>
            <td>${week.orderInPlan}</td>
            <c:forEach var="day" items="${week.days}">
               <td>
                   ${day.date}
                   <c:forEach var="training" items="${day.trainings}">
                 <a href="/training/${training.id}/athleteView/"><br>${training.orderInDay}. ${training.shortCut}</a>

                </c:forEach>
               </td>
            </c:forEach>
        </tr>

    </c:forEach>
</table>

</body>
</html>
