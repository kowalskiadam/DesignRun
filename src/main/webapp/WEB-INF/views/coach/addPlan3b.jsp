<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 04.08.2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add plan 3/x</h1>

<form:form method="post" modelAttribute="trainingForms">
    <table>
    <c:forEach var="trainingForm" items="${trainingForms}">
        <tr>
        <td>Lp. ${trainingForm.id}</td>
        <td>${trainingForm.dayOfWeek}</td>
        <td>Order: ${trainingForm.order}</td>
         <td>
             <label>Traning type:</label>
             <form:select path="${trainingForm.trainingType}" items="${planForm.trainingTypes}" itemValue="id" itemLabel="name"/>
        </td>
            <td>
                <label>Distance:</label>
                <form:input type="number" path="${trainingForm.distance}" value="10"/>
            </td>

    </tr>
    </c:forEach>
    </table>
    <br>

    <input type="submit" value="Add">

</form:form>







<bR>
<a href="addPlan2">Back to page 2</a><br/>

</body>
</html>
