<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-form-style.css">

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<div class="container">
    <div class="div">
        <span class="span">Добавить покупку</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>

            <p>
                <label for="customerId">Покупатель</label>
                <select name="customerId" id="customerId" required>
                    <option value="">не выбрано</option>
                    <c:forEach var="customer" items="${requestScope.customers}">
                        <option value="${customer.id}">
                                ${customer.name}, ${customer.surname}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="machineId">Аппарат</label>
                <select name="machineId" id="machineId" required>
                    <option value="">не выбрано</option>
                    <c:forEach var="machine" items="${requestScope.machines}">
                        <option value="${machine.id}">
                            <c:if test="${machine ne null}">
                                ${machine.address}
                            </c:if>
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="productId">Продукция</label>
                <select name="productId" id="productId" required>
                    <option value="">не выбрано</option>
                    <c:forEach var="product" items="${requestScope.products}">
                        <option value="${product.id}">
                            <c:if test="${product ne null}">
                                ${product.name}, ${product.price}
                            </c:if>
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="price">Цена:</label>
                <input type="number" step="0.1" name="price" id="price" placeholder="цена">
            </p>

            <p>
                <label for="discountId">Скидка</label>
                <select name="discountId" id="discountId">
                    <option value="">не выбрано</option>
                    <c:forEach var="discount" items="${requestScope.discounts}">
                        <option value="${discount.id}">
                                ${discount.percent}, ${discount.amount}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="amount">Сумма:</label>
                <input type="number" step="0.1" name="amount" id="amount" placeholder="сумма">
            </p>

            <p>
                <label for="typePaymentId">Тип оплаты</label>
                <select name="typePaymentId" id="typePaymentId" required>
                    <option value="">не выбрано</option>
                    <c:forEach var="typePayment" items="${requestScope.typePayments}">
                        <option value="${typePayment.id}">
                                ${typePayment.name}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <div id='creditCard'>
                <label for="creditCard">Карта</label>
                <select name="creditCardId">
                    <option value="">не выбрано</option>
                    <c:forEach var="creditCardItem" items="${requestScope.creditCards}">
                        <option value="${creditCardItem.id}">
                                ${creditCardItem.number} (${creditCardItem.customer.name}, ${creditCardItem.customer.surname})
                        </option>
                    </c:forEach>
                </select>
            </div>


            <p>
                <button type="submit" name="command" value="newPurchase" class="buttonClass">СОХРАНИТЬ</button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
