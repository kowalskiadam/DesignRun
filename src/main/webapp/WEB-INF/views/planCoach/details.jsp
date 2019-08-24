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


<div class="info box4inline">
    name: ${plan.name}
</div>

<div class="info box4inline">
    Athlete login: ${plan.athlete.login}

</div>

<div class="info box4inline">
    Numbers of week: ${plan.weeksNumber}

</div>

<div class="info box4inline">
    Start date: ${plan.startDay}

</div>

<div class="success box4inline">
    <a href="/plan/${plan.id}/coach/allTrainingsByWeeks/">Show all trainings by weeks</a><br/>

</div>

<div class="success box4inline">
    <a href="/plan/${plan.id}/coach/allTrainings/">Show all trainings</a><br/>

</div>

<br>
<br>
</body>
</html>
