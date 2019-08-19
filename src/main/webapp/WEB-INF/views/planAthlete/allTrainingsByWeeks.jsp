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
