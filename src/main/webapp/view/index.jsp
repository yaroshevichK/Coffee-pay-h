<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Coffee pay</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<c:if test="${sessionScope.loggedUser ne null}">
    <div style="display:flex;">
        <div>
            <jsp:include page="../headerMenu.jsp"/>
        </div>
    </div>
</c:if>

</body>
</html>