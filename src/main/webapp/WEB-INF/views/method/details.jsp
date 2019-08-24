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

<h1>Method details</h1>
<form:form method="post" modelAttribute="method">

    <label for="nameId">Name:</label>
    <form:input type="text" path="name" id="nameId"/>
    <form:errors path="name" />

    <br>

    <label for="descriptionId">Name:</label>
    <form:textarea type="text" rows="10" cols="50" path="shortDescription" id="descriptionId"/>
    <form:errors path="shortDescription" />

    <br>

    <input type="submit" value="Update">

</form:form>

<h3>Operations</h3>

<a href="addTrainingType">Add new training type</a><br/>


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
                    <a href="/trainingType/${trainingType.id}/details/">Details</a>
                </td>
            </tr>

        </c:forEach>
    </table>



</body>
</html>
