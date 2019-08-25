<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="/WEB-INF/fragments/headerCoach.jspf" %>
<div class="image1" style="background-image:url(<c:url value='/resources/images/running1.jpeg' />)">
<br>
    <div class="info box4inlineSmall">
        Training name: ${training.name}

    </div>

    <div class="info box4inlineSmall">
        Date: ${training.day.date}
    </div>

    <div class="info box4inlineSmall">
        Order in day: ${training.orderInDay}
    </div>

    <div class="info box4inlineSmall">
        Distance: ${training.distance}
    </div>

    <div class="info box4inlineSmall">
        Description: ${training.description}
    </div>


    <div class="info box4inlineSmall">
        Training type: ${training.trainingType.name}
    </div>


    <div class="info box4inlineSmall">
        Athlete comment: ${training.athleteComment}
    </div>

    <div class="form-style-6">
<h1>Give feedback</h1>
<form:form method="post" modelAttribute="training">

    <label for="nameId">Name:</label>
    <form:textarea type="text" rows="10" cols="50" path="coachFeedback" id="nameId" value="${training.coachFeedback}"/>

    <br>

    <input type="submit" value="Add">

</form:form>
</div>
</div>
</body>
</html>
