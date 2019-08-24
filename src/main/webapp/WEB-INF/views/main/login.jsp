<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<h1 style="text-align: center">Login page</h1>
${SPRING_SECURITY_LAST_EXCEPTION.message}
<div class="login-page">
<form class="form-style-6" action="login" method='POST'>

    <table >
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>

    <a href="/newCoach">Create new coach</a>

</form>
</div>
</body>
</html>