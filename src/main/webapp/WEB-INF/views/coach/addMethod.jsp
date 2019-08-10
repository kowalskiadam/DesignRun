<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 31.07.2019
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add method</h1>
<form:form method="post" modelAttribute="method">

    <label for="nameId">Name:</label>
    <form:input type="text" path="name" id="nameId" value="testPlan"/>

    <br>

    <label for="descriptionId">Description:</label>
    <form:textarea type="text" rows="10" cols="50" path="description" id="descriptionId"/>

    <br>

    <input type="submit" value="Add">

</form:form>
</body>
</html>
