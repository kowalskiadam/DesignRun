<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Adamk
  Date: 10.08.2019
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Name; ${training.name}
Date: ${training.day.date}
Order in day: ${training.orderInDay}
Distance: ${training.distance}
Description: ${training.description}
Training type: ${training.trainingType.name}
Training type description: ${training.trainingType.description}
</body>
</html>
