<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scroll-table.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleCustomer" value="Customer"/>
<c:set var="access" value="${false}"/>
<c:if test="${authority.contains(roleCustomer)}">
    <c:set var="access" value="${true}"/>
</c:if>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../../header.jsp"/>

<div style="display:flex;">
    <c:if test="${sessionScope.loggedUser ne null}">
        <div>
            <jsp:include page="../../headerMenu.jsp"/>
        </div>
    </c:if>

    <c:if test="${not access}">
        <p>
            ДОСТУП ОГРАНИЧЕН
        </p>
    </c:if>

    <c:if test="${access}">
        <div class="div-with-table">
            <h3>ЗАКАЗ</h3>
            <br>
            <form action="coffee-manage" class="validate-form" method="post">
                <div>
                    <div>
                        <img width="400" src="${pageContext.request.contextPath}/images/wallpaper.jpg">
                    </div>
                    <div>
                        <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                        <button type="submit" name="command" value="chooseMachine" class="buttonClass">
                            ВЫБРАТЬ АППАРАТ
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
