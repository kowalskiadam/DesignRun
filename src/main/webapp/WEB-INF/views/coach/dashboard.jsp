<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 28.07.2019
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Coach Dashboard</h1>
Id: ${coach.id};
Login: ${coach.login};
<br>
<a href="athleteList">Manage athletes</a><br/>
<a href="methodsList">Training methods</a><br/>
<a href="advanced">Advanced</a><br/>
</body>
</html>
