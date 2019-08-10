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
<h1>Add plan 2/x</h1>
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

</body>
</html>
