<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">

    <div class="form-style-6">
        <h1>Add plan 1/2</h1>
<h3>Find athlete</h3>
<form method="post">
    Login to find: <input type="text" name="loginToFind"><br>
    <input type="submit" value="Submit">
</form>
    </div>
</div>

</body>
</html>
