<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>
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
