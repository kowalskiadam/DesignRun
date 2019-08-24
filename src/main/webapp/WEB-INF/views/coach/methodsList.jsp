
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Title</title>
    <script>
        function confirmHide(id, name) {
            if (confirm("Would you like to hide method \"" + name + "\"")) {
                window.location.href = "/method/" + id + "/hide";
            }
        }
    </script>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
    <br>
    <p class="table-head">Method list</p>
<table class="container">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Opertations</th>
    </tr>
    <c:forEach var="method" items="${coachMethods}">
        <tr>
            <td>${method.name}</td>
            <td>${method.shortDescription}</td>
            <td>
                <a class="training-link" href="/method/${method.id}/details/">Details</a>
                <a class="training-link" href="#" onclick="confirmHide(${method.id}, '${method.name}')">Hide</a>
            </td>
        </tr>

    </c:forEach>
</table>
</div>

</body>
</html>
