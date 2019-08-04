<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 30.07.2019
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
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
</head>
<body>
<a href="dashboard">Back to dashboard</a><br/>
<a href="addMethod">Create new training method</a><br/>

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
            <td>${method.description}</td>
            <td>
                <a href="#" onclick="confirmUnHide(${method.id}, '${method.name}')">Unhide</a>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
