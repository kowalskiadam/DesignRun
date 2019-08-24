<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div class="form-style-6">
<h1>Add plan 2/2</h1>
<form:form method="post" modelAttribute="planForm">

    <label for="mondaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" value="1" path="mondaysTrainings" id="mondaysTrainingsId"/>

    <br>

    <label for="tuesdaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" value="0" path="tuesdaysTrainings" id="tuesdaysTrainingsId"/>

    <br>

    <label for="wednesdaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" value="2" path="wednesdaysTrainings" id="wednesdaysTrainingsId"/>

    <br>

    <label for="thursdaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" value="1" path="thursdaysTrainings" id="thursdaysTrainingsId"/>

    <br>

    <label for="fridaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" value="0" path="fridaysTrainings" id="fridaysTrainingsId"/>

    <br>

    <label for="saturdaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" value="2" path="saturdaysTrainings" id="saturdaysTrainingsId"/>

    <br>

    <label for="sundaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" value="1" path="sundaysTrainings" id="sundaysTrainingsId"/>

    <br>

    <label for="defaultTrainingTypeId">Default traning type:</label>
    <form:select path="defaultTrainingType" items="${planForm.trainingTypes}" itemValue="id" itemLabel="name" id="defaultTrainingTypeId"/>

    <br>

    <label for="defaultDistance">Default distance:</label>
    <form:input type="number" value="10" path="defaultDistance" id="defaultDistance"/>

    <br>


    <input type="submit" value="Add">

</form:form>
<bR>
<a href="addPlan1">Back to page 1</a><br/>
</div>
</div>
</body>
</html>
