<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 02.08.2019
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Athlete dashboard</h1>
Id: ${athlete.id};
Login: ${athlete.login};
First name: ${athlete.firstName};
Last name: ${athlete.lastName};
<br>
<br>
<a href="planList">Plan's list</a><br/>
<a href="advanced">Advanced</a><br/>

</body>
</html>