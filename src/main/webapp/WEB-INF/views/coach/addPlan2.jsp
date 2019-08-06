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

    <label for="mondaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" path="mondaysTrainings" id="mondaysTrainingsId"/>

    <br>

    <label for="tuesdaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" path="tuesdaysTrainings" id="tuesdaysTrainingsId"/>

    <br>

    <label for="wednesdaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" path="wednesdaysTrainings" id="wednesdaysTrainingsId"/>

    <br>

    <label for="thursdaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" path="thursdaysTrainings" id="thursdaysTrainingsId"/>

    <br>

    <label for="fridaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" path="fridaysTrainings" id="fridaysTrainingsId"/>

    <br>

    <label for="saturdaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" path="saturdaysTrainings" id="saturdaysTrainingsId"/>

    <br>

    <label for="sundaysTrainingsId">Number of weeks in plan:</label>
    <form:input type="number" path="sundaysTrainings" id="sundaysTrainingsId"/>

    <br>


    <input type="submit" value="Add">

</form:form>
<bR>
<a href="addPlan1">Back to page 1</a><br/>

</body>
</html>
