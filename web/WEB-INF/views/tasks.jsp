<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>

<div class="listTitle">
    <h1>Recent Spittles</h1>
    <ul class="spittleList">
        <c:forEach items="${tasklist}" var="task" >
            <li id="spittle_<c:out value="task.id"/>">
                <div class="spittleMessage"><c:out value="${task.description}" /></div>
                <div>
                    <span class="spittleTime"><c:out value="${task.content}" /></span>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>