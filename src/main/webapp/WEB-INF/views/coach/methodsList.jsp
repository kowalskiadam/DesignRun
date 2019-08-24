
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

<br>
<h2>methodsList</h2>
<br>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
    </tr>
    <c:forEach var="method" items="${coachMethods}">
        <tr>
            <td>${method.name}</td>
            <td>${method.shortDescription}</td>
            <td>
                <a href="/method/${method.id}/details/">Details</a>
                <a href="#" onclick="confirmHide(${method.id}, '${method.name}')">Hide</a>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
