
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
    <p class="table-head">Your plans</p>
<table class="container">
    <tr>
        <th>Plan's name</th>
        <th>Start date</th>
        <th>Coach login</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="plan" items="${plans}">
        <tr>
            <td>${plan.name}</td>
            <td>${plan.startDay}</td>
            <td>${plan.coach.login}</td>
            <td>
                <a class="training-link" href="/plan/${plan.id}/athlete/allTrainingsByWeeks/">Show trainings</a>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
