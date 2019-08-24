<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 28.07.2019
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="icon" type="image/png" href="/resources/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">

    <link href='<c:url value="/resources/images/icons/favicon.ico"/>' rel="icon" type="image/png">
    <link href='<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet" type="text/css">
    <link href='<c:url value="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">
    <link href='<c:url value="/resources/vendor/animate/animate.css"/>' rel="stylesheet" type="text/css">
    <link href='<c:url value="/resources/vendor/select2/select2.min.css"/>' rel="stylesheet" type="text/css">
    <link href='<c:url value="/resources/vendor/perfect-scrollbar/perfect-scrollbar.css"/>' rel="stylesheet" type="text/css">
    <link href='<c:url value="/resources/css/util.css"/>' rel="stylesheet" type="text/css">
    <link href='<c:url value="/resources/css/main.css"/>' rel="stylesheet" type="text/css">





</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>

<h1>Coach Dashboard</h1>
Id: ${coach.id};
Login: ${coach.login};
<br>

<a href="plansList">Manage plans</a><br/>
<a href="athleteList">Manage athletes</a><br/>
<a href="methodsList">Training methods</a><br/>
<a href="advanced">Advanced</a><br/>







<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>
