<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 31.07.2019
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Method details</h1>
<form:form method="post" modelAttribute="method">

    <label for="nameId">Name:</label>
    <form:input type="text" path="name" id="nameId"/>

    <br>

    <label for="descriptionId">Name:</label>
    <form:textarea type="text" rows="10" cols="50" path="description" id="descriptionId"/>

    <br>

    <input type="submit" value="Update">

</form:form>

<h3>Operations</h3>

<a href="addTraningType">Add new training type</a><br/>


    <h3>Training types list</h3>
  <table>
        <tr>
            <th>Name</th>
            <th>shortCut</th>
            <th>minDistance</th>
            <th>maxDistance</th>
        </tr>
        <c:forEach var="trainingType" items="${trainingTypes}">
            <tr>
                <td>${trainingType.name}</td>
                <td>${trainingType.shortCut}</td>
                <td>${trainingType.minDistance}</td>
                <td>${trainingType.maxDistance}</td>
                <td>
                    <a href="/traningType/${trainingType.id}/details/">Details</a>
                </td>
            </tr>

        </c:forEach>
    </table>



</body>
</html>
