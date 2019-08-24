
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>

<h1>Create new training type</h1>
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

    <input type="submit" value="Create">

</form:form>

</body>
</html>
