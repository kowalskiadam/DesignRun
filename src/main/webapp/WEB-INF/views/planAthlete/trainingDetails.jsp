<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/WEB-INF/fragments/headerAthlete.jspf" %>

<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">

    <div class="info box4inline">
        Training name: ${training.name}

    </div>

    <div class="info box4inline">
        Date: ${training.day.date}
    </div>

    <div class="info box4inline">
        Order in day: ${training.orderInDay}
    </div>

    <div class="info box4inline">
        Distamce: ${training.distance}
    </div>

    <div class="info box4inline">
         Description: ${training.description}
    </div>


    <div class="info box4inline">
       Training type: ${training.trainingType.name}
    </div>


    <div class="info box4inline">
        Training type decription: ${training.trainingType.description}
    </div>

</div>


</body>
</html>
