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
    <p class="table-head">Training types list</p>
    <table class="container">
        <tr>
            <th>Name</th>
            <th>shortCut</th>
            <th>minDistance</th>
            <th>maxDistance</th>
            <th>Edit</th>
        </tr>
        <c:forEach var="trainingType" items="${trainingTypes}">
            <tr>
                <td>${trainingType.name}</td>
                <td>${trainingType.shortCut}</td>
                <td>${trainingType.minDistance}</td>
                <td>${trainingType.maxDistance}</td>
                <td>
                    <a href="/trainingType/${trainingType.id}/details/">Edit</a>
                </td>
            </tr>

        </c:forEach>
    </table>


</div>


</body>
</html>
