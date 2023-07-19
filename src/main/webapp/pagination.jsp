<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page-style.css">

<%--@elvariable id="paramUrl" type="java.lang.String"--%>
<%--@elvariable id="pageSize" type="java.lang.Integer"--%>
<%--@elvariable id="pageNumber" type="java.lang.Integer"--%>
<%--@elvariable id="lastPage" type="java.lang.Integer"--%>

<nav>
    <label>
        <select name="pageSize" onchange="document.location=this.options[this.selectedIndex].value">
            <c:choose>

                <c:when test="${pageSize eq 3}">
                    <option selected hidden>3</option>
                    <option value="${paramUrl}&pageSize=5&pageNumber=${pageNumber}">
                        5
                    </option>
                    <option value="${paramUrl}&pageSize=10&pageNumber=${pageNumber}">
                        10
                    </option>
                </c:when>
                <c:when test="${pageSize eq 5}">
                    <option value="${paramUrl}&pageSize=3&pageNumber=${pageNumber}">
                        3
                    </option>
                    <option selected hidden>5</option>
                    <option value="${paramUrl}&pageSize=10&pageNumber=${pageNumber}">
                        10
                    </option>
                </c:when>
                <c:when test="${pageSize eq 10}">
                    <option value="${paramUrl}&pageSize=3&pageNumber=${pageNumber}">
                        3
                    </option>
                    <option value="${paramUrl}&pageSize=5&pageNumber=${pageNumber}">
                        5
                    </option>
                    <option selected hidden>10</option>
                </c:when>
                <c:otherwise>
                    <option selected
                            value="${paramUrl}&pageSize=3&pageNumber=${pageNumber}">
                        3
                    </option>
                    <option value="${paramUrl}&pageSize=5&pageNumber=${pageNumber}">
                        5
                    </option>
                    <option value="${paramUrl}&pageSize=10&pageNumber=${pageNumber}">
                        10
                    </option>
                </c:otherwise>
            </c:choose>
        </select>
    </label>

    <ul class="pagination">

        <c:if test="${pageNumber ne 1}">
            <li class="li">
                <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber-1}">предыдущая</a>
            </li>
        </c:if>

        <c:choose>

            <c:when test="${lastPage lt 10}">
                <c:forEach begin="1" end="${lastPage}" var="i">
                    <c:choose>
                        <c:when test="${pageNumber eq i}">
                            <li class="li"><a class="active">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="li">
                                <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>


            <c:when test="${pageNumber le 5}">
                <c:forEach begin="1" end="5" var="i">
                    <c:choose>
                        <c:when test="${pageNumber eq i}">
                            <li class="li"><a class="active">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="li">
                                <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <li class="li">
                    <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=6">...</a>
                </li>

                <li class="li">
                    <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${lastPage}">${lastPage}</a>
                </li>

            </c:when>

            <c:when test="${pageNumber ge lastPage-5}">
                <li class="li">
                    <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=1">1</a>
                </li>

                <li class="li">
                    <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${lastPage-6}">...</a>
                </li>

                <c:forEach begin="${lastPage-5}" end="${lastPage}" var="i">
                    <c:choose>
                        <c:when test="${pageNumber eq i}">
                            <li class="active"><a>${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </c:when>

            <c:otherwise>
                <li class="li">
                    <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=1">1</a>
                </li>

                <li style="margin-right: 5px">
                    <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber-3}">...</a>
                </li>

                <c:forEach begin="${pageNumber-2}" end="${pageNumber+2}" var="i">
                    <c:choose>
                        <c:when test="${pageNumber eq i}">
                            <li class="active"><a>${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <li class="li">
                    <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber+3}">...</a>
                </li>

                <li class="li">
                    <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${lastPage}">${lastPage}</a>
                </li>
            </c:otherwise>

        </c:choose>

        <c:if test="${pageNumber lt lastPage}">
            <li class="li">
                <a href="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber+1}">следующая</a>
            </li>
        </c:if>
    </ul>


</nav>