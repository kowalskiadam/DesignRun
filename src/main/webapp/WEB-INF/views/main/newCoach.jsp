<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
    <div class="form-style-6">

<h1>Create new coach</h1>
<br/>

        <p class="error">${message}</p>
<form:form method="post" modelAttribute="coach">

    <label for="loginId">Login:</label>
    <form:input type="text" path="login" id="loginId"/>
    <form:errors path="login"/>

    <label for="passwordId">Password:</label>
    <form:input type="password" path="password" id="passwordId"/>
    <form:errors path="password"/>

    <label for="aboutMeShortId">Description:</label>
    <form:textarea type="text" rows="10" cols="50" path="aboutMeShort" id="aboutMeShortId"/>
    <form:errors path="aboutMeShort" />

    <br>

    <input type="submit" value="Create">

</form:form>
    </div>
</div>


</body>
</html>
