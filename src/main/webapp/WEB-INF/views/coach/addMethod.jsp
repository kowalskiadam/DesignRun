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



<div class="image1" style="background-image:url(<c:url value='/resources/images/mountain.jpg' />)">
    <h1>Contact Us</h1>
    <form>
        <input type="text" name="field1" placeholder="Your Name" />
        <input type="email" name="field2" placeholder="Email Address" />
        <textarea name="field3" placeholder="Type your Message"></textarea>
        <input type="submit" value="Send" />
    </form>



<h1>Add method</h1>

<div class="form-style-6">
    <h1>Create new method</h1>

    <form:form method="post" modelAttribute="method">

    <label for="nameId">Name:</label>
    <form:input type="text" path="name" id="nameId" value="testPlan"/>
    <form:errors path="name" />

    <br>

    <label for="descriptionId">Description:</label>
    <form:textarea type="text" rows="10" cols="50" path="shortDescription" id="descriptionId"/>
    <form:errors path="shortDescription" />

    <br>

    <input type="submit" value="Add">

</form:form>
</div>
</div>
</body>
</html>
