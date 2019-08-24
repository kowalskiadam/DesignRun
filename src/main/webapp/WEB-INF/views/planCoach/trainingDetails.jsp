<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script>
        function confirmDelete(id, name) {
            if (confirm("Would you like to delete training " + name )) {
                window.location.href = "/training/" + id + "/delete";
            }
        }
    </script>
    <link href='<c:url value="/resources/css/style.css"></c:url>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>

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

<a href="#" onclick="confirmDelete(${training.id}, '${training.name}')">Delete</a>


</body>
</html>
