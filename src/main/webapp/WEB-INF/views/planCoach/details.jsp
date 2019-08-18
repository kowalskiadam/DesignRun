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
