<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<h1>Add plan 1/x</h1>
<form:form method="post" modelAttribute="planForm">

    <label for="nameId">Name:</label>
    <form:input type="text" path="name" id="nameId" value="testPlan"/>

    <br>

    <label for="athleteId">Athlete:</label>
    <form:select path="athlete" items="${planForm.coachAthletes}" itemValue="id" itemLabel="login" id="athleteId"/>

    <br>

    <br>

    <label for="methodId">Athlete:</label>
    <form:select path="method" items="${planForm.coachMethods}" itemValue="id" itemLabel="name" id="methodId"/>

    <br>

    <label for="mondaysId">Athlete:</label>
    <form:select path="startDay" items="${planForm.mondays}" id="mondaysId"/>

    <br>

    <label for="weeksNumberid">Number of weeks in plan:</label>
    <form:input type="number" value = "20" path="weeksNumber" id="weeksNumberid"/>


    <input type="submit" value="Add">

</form:form>
</body>
</html>
