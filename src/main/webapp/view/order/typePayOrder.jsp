<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/radio-style.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleCustomer" value="Customer"/>
<c:set var="access" value="${false}"/>
<c:if test="${authority.contains(roleCustomer)}">
    <c:set var="access" value="${true}"/>
</c:if>
<c:set var="products" value="${requestScope.products}"/>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<div class="container">
    <div class="div">
        <span class="span">Подтверждение покупки</span>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">

            <form class="validate-form" action="coffee-manage">
                <input type="hidden" name="customerId" value="${requestScope.customer.id}"/>
                <input type="hidden" name="machineId" value="${requestScope.machine.id}"/>
                <input type="hidden" name="productId" value="${requestScope.product.id}"/>
                <input type="hidden" name="discountId" value="${requestScope.discount.id}"/>
                <input type="hidden" name="price" value="${requestScope.product.price}"/>
                <input type="hidden" name="amount" value="${requestScope.amount}"/>

                <c:if test="${requestScope.customer ne null}">
                    <p>
                        Покупатель: ${requestScope.customer.name}, ${requestScope.customer.surname}
                    </p>
                </c:if>

                <c:if test="${requestScope.machine ne null}">
                    <b>Аппарат:</b>
                    <p>
                            ${requestScope.machine.model.brand}, ${requestScope.machine.model.nameModel}
                    </p>

                    <p>
                            ${requestScope.machine.address.city}, ${requestScope.machine.address.street}
                    </p>
                </c:if>

                <c:if test="${requestScope.product ne null}">
                    <p>
                        <b>Продукция: </b>${requestScope.product.name}
                    </p>
                    <p>
                        <b>Цена: </b>${requestScope.product.price}
                    </p>
                </c:if>

                <div>
                    <script>
                        function SelectedCreditCard(creditCard, phoneNumber) {
                            document.getElementById("creditCard").style.display = 'none';
                            document.getElementById("phoneNumber").style.display = 'none';


                            if (creditCard.valueOf() === 'true') {
                                document.getElementById("creditCard").style.display = 'block';
                            } else {
                                document.getElementById("creditCard").style.display = 'none';
                            }

                            if (phoneNumber.valueOf() === 'true') {
                                document.getElementById("phoneNumber").style.display = 'block';
                            } else {
                                document.getElementById("phoneNumber").style.display = 'none';
                            }
                        }
                    </script>

                    <div>
                        <label>Типы оплаты</label>
                        <c:forEach var="typePayment" items="${requestScope.typePayments}">
                            <div class="form_radio_btn">
                                <input id="typePayment${typePayment.id}" type="radio" name="typePaymentId"
                                       value="${typePayment.id}"
                                       onChange="SelectedCreditCard('${typePayment.useCreditCard}','${typePayment.usePhoneNumber}')">
                                <label for="typePayment${typePayment.id}">${typePayment.name}</label>
                            </div>
                        </c:forEach>
                    </div>

                </div>

                <div id='creditCard' style='display: none;'>
                    <label for="creditCard">Карта</label>
                    <select name="creditCardId">
                        <option value="">не выбрано</option>
                        <c:forEach var="creditCardItem" items="${requestScope.creditCards}">
                            <option value="${creditCardItem.id}">${creditCardItem.number}</option>
                        </c:forEach>
                    </select>
                </div>

                <div id='phoneNumber' style='display: none;'>
                    <label for="phoneNumber">Номер телефона: ${requestScope.customer.phone}</label>
                </div>

                <c:set var="discount" value="${requestScope.discount}"/>
                <p>
                    <b>Скидка: </b>${requestScope.discount.percent} %
                </p>

                <p><b>
                    Сумма к оплате: ${requestScope.amount}
                </b></p>

                <p>
                    <button type="submit" name="command" value="newOrderPay" class="buttonClass"
                            formmethod="post">
                        ОПЛАТИТЬ
                    </button>

                    <button type="submit" name="command" value="mainOrder" class="buttonClass">
                        ОТМЕНА
                    </button>
                </p>

            </form>
        </c:if>
    </div>
</div>

</body>
</html>
