<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 28.07.2019
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create new trainer</h1>
<br/>

${message}
Create new coach
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



</body>
</html>
