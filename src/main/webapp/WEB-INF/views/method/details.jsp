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
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">

    <div class="info box4inline">
        Method name: ${method.name}

    </div>

    <div class="info box4inline">
        Short description: ${method.shortDescription}

    </div>

    <div class="success box4inline">
        <a href="/method/${method.id}/update/">Update method information</a><br/>

    </div>

    <div class="success box4inline">
        <a href="/method/${method.id}/addTrainingType/">Add new training types</a><br/>

    </div>

    <div class="success box4inline">
        <a href="/method/${method.id}/trainingTypesList/">Show all trainings types</a><br/>

    </div>

</div>


</body>
</html>
