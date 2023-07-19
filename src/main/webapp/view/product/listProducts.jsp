<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleAdministrator" value="Administrator"/>
<c:set var="roleManager" value="Manager"/>
<c:set var="access" value="${false}"/>
<c:if test="${authority.contains(roleAdministrator) or authority.contains(roleManager)}">
    <c:set var="access" value="${true}"/>
</c:if>

<c:set var="paramUrl" value="${requestScope.paramUrl}" scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPage" value="${requestScope.pageable.lastPage}" scope="request"/>
<c:set var="products" value="${requestScope.pageable.records}" scope="request"/>

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

    <div class="div-with-table">
        <h3 class="h1">Список продукции</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">

            <form action="coffee-manage" method="get">
                <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                <button type="submit" name="command" value="newProduct" class="buttonClass">ДОБАВИТЬ ПРОДУКЦИЮ</button>
            </form>
            <br>

            <form action="coffee-manage">
                <label for="sortField">Сортировать по: </label>
                <select id="sortField" name="sortField">
                    <option value="${null}">не выбрано</option>
                    <c:choose>
                        <c:when test="${requestScope.sortField eq 'price'}">
                            <option
                                    value="name">
                                по имени
                            </option>
                            <option selected
                                    value="price">
                                по цене
                            </option>
                        </c:when>
                        <c:when test="${requestScope.sortField eq 'name'}">
                            <option selected
                                    value="name">
                                по имени
                            </option>
                            <option value="price">
                                по цене
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option
                                    value="name">
                                по имени
                            </option>
                            <option value="price">
                                по цене
                            </option>
                        </c:otherwise>
                    </c:choose>
                </select>

                <label><input name="searchName" placeholder="название" value="${requestScope.searchName}"> </label>
                <label><input type="number" step="0.01" name="searchPrice" placeholder="цена"
                              value="${requestScope.searchPrice}"></label>
                <input type="hidden" name="pageNumber" value="${pageNumber}">
                <input type="hidden" name="pageSize" value="${pageSize}">
                <button type="submit" name="command" value="products" class="buttonClass">ПОИСК И СОРТИРОВКА</button>
            </form>

            <c:choose>
                <c:when test="${products eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${products ne null and products.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table">
                        <tr>
                            <th>№</th>
                            <th>Название</th>
                            <th>Цена</th>
                            <c:if test="${access}">
                                <th colspan="2">Действие</th>
                            </c:if>
                        </tr>

                        <c:choose>
                            <c:when test="${pageNumber ne 0}">
                                <c:set var="lastCount" value="${(pageNumber-1)*pageSize}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="lastCount" value="0"/>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="product" items="${products}" varStatus="status">
                            <tr>
                                <td>${lastCount+status.count}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                                <c:if test="${access}">
                                    <td>
                                        <form action="coffee-manage" method="get">
                                            <input type="hidden" name="productId" value="${product.id}">
                                            <input type="hidden" name="prevURL"
                                                   value="/coffee-manage?${pageContext.request.queryString}"/>
                                            <button type="submit" name="command" value="updateProduct"
                                                    class="buttonClass">
                                                РЕДАКТИРОВАТЬ
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="coffee-manage" method="post">
                                            <c:choose>
                                                <c:when test="${products.size() eq 1}">
                                                    <input type="hidden" name="prevURL"
                                                           value="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="prevURL"
                                                           value="/coffee-manage?${pageContext.request.queryString}"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <input type="hidden" name="productId" value="${product.id}">
                                            <button type="submit" name="command" value="deleteProduct"
                                                    class="buttonClass">
                                                УДАЛИТЬ
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>

                    </table>
                </c:otherwise>

            </c:choose>

            <br>

            <jsp:include page="../../pagination.jsp"/>

        </c:if>
    </div>
</div>
</body>
</html>
