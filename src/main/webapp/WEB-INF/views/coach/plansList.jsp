<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 04.08.2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Manage plans</h1>
<a href="addPlan1">Add plan</a><br/>

<h3>Plan list</h3>
<table>
    <tr>
        <th>Name</th>
        <th>StartDay</th>
        <th>Number of weeks</th>
        <th>Athlete</th>
        <th>Method</th>

    </tr>
    <c:forEach var="plan" items="${coachPlans}">
        <tr>
            <td>${plan.name}</td>
            <td>${plan.startDay}</td>
            <td>${plan.weeksNumber}</td>
            <td>${plan.athlete.login}</td>
            <td>${plan.method.name}</td>
            <td><a href="/plan/${plan.id}/coach/details/">Details</a>
            </td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
