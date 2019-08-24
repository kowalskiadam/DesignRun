<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>

<table>
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
                   <a href="/plan/${week.plan.id}/coach/addTraining/${day.date}"><br>${day.date}</a>
                   <c:forEach var="training" items="${day.trainings}">
                 <a href="/training/${training.id}/details/"><br>${training.orderInDay}. ${training.shortCut}</a>

                </c:forEach>
               </td>
            </c:forEach>
        </tr>

    </c:forEach>
</table>

</body>
</html>
