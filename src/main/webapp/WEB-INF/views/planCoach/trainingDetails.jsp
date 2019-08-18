<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 18.08.2019
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="training">

   <form:input type="hidden" path="id"/>
    <form:input type="hidden" path="shortCut"/>
    <form:input type="hidden" path="athleteComment"/>

    <label for="dayId">Day:</label>
    <form:select path="day" items="${days}" itemValue="id" itemLabel="date" id="dayId"/>
    <form:errors path="day" />
    <br>

    <label for="trainingTypeId">Training type:</label>
    <form:select path="trainingType" items="${trainingTypes}" itemValue="id" itemLabel="name" id="trainingTypeId"/>
    <form:errors path="trainingType" />
    <br>

    <label for="orderInDayId">Order in day:</label>
    <form:input type="number" path="orderInDay" id="orderInDayId"/>
    <form:errors path="orderInDay" />
    <br>

    <label for="distanceId">Distance:</label>
    <form:input type="number" path="distance" id="distanceId"/>
    <form:errors path="distance" />
    <br>

    <label for="nameId">Name:</label>
    <form:input type="text" path="name" id="nameId"/>
    <form:errors path="name" />
    <br>

    <label for="descriptionId">Description:</label>
    <form:textarea type="text" rows="10" cols="50" path="description" id="descriptionId"/>
    <form:errors path="description" />

    <br>

    <input type="submit" value="Update">

</form:form>
</body>
</html>
