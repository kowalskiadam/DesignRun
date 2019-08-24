
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
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
    <div class="form-style-6">
<h1>Create new athlete</h1>
<br/>

${message}
<form:form method="post" modelAttribute="athlete">

    <label for="loginId">Login:</label>
    <form:input type="text" path="login" id="loginId"/>
    <form:errors path="login"/>

    <label for="passwordId">Password:</label>
    <form:input type="password" path="password" id="passwordId"/>
    <form:errors path="password"/>

    <label for="firstNameId">First name:</label>
    <form:input type="text" path="firstName" id="firstNameId"/>
    <form:errors path="firstName"/>

    <label for="lastNameId">Last name:</label>
    <form:input type="text" path="lastName" id="lastNameId"/>
    <form:errors path="lastName"/>

    <br>

    <input type="submit" value="Create">

</form:form>
    </div>
</div>


</body>
</html>
