<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>

Plan id: ${plan.id};
Plan name: ${plan.name};
Athlete login: ${plan.athlete.login};
Numbers of week: ${plan.weeksNumber};
Start date: ${plan.startDay};
<br>
<br>
<a href="/plan/${plan.id}/coach/allTrainingsByWeeks/">All trainings by weeks</a><br/>
<a href="/plan/${plan.id}/coach/allTrainings/">All trainings</a><br/>
</body>
</html>
