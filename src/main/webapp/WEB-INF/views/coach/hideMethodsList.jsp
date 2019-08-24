
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script>
        function confirmUnHide(id, name) {
            if (confirm("Would you like to unhide method \"" + name + "\"")) {
                window.location.href = "/method/" + id + "/unhide";
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
    <c:forEach var="method" items="${coachHideMethods}">
        <tr>
            <td>${method.name}</td>
            <td>${method.shortDescription}</td>
            <td>
                <a href="#" onclick="confirmUnHide(${method.id}, '${method.name}')">Unhide</a>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
