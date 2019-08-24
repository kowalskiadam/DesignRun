<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
    <div class="error box" style="height: 200px;">
        <br>
        <h1>Add athlete result</h1>
        <p><c:out value="${message}"/></p>
        <a href="athleteList">Back to athletes list</a>
    </div>



</div>
</body>
</html>
