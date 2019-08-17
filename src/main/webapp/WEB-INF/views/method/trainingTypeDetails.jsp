<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 04.08.2019
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create new trainer</h1>
<br/>

<form:form method="post" modelAttribute="trainingType">

    <label for="nameId">Name:</label>
    <form:input type="text" path="name" id="nameId"/>
    <form:errors path="name" />


    <label for="shortcutId">Shortcut:</label>
    <form:input type="text" path="shortCut" id="shortcutId"/>
    <form:errors path="shortCut" />

    <label for="descriptionId">Description:</label>
    <form:textarea type="text" rows="10" cols="50" path="description" id="descriptionId"/>
    <form:errors path="description" />

    <label for="minDistanceId">Min distance:</label>
    <form:input type="text" path="minDistance" id="minDistanceId"/>
    <form:errors path="minDistance" />

    <label for="maxDistanceId">Max distance:</label>
    <form:input type="text" path="maxDistance" id="maxDistanceId"/>
    <form:errors path="maxDistance" />

    <br>

    <input type="submit" value="Update">

</form:form>

</body>
</html>
