package it.academy.utils.constants;

public class DataUI {
    public static final String LOGGED_USER = "loggedUser";
    public static final String AUTHORITY = "authority";
    public static final String URL_SERVLET = "/coffee-manage";
    public static final String URL_AUTH_SERVLET = "/auth";
    public static final String URL_SEC_FILTER = "/coffee-manage/*";
    public static final String URL_FILTER = "/*";
    public static final String ATTR_COMMAND = "command";
    public static final String GET_REQUEST = "GET";
    public static final String POST_REQUEST = "POST";
    public static final String UTF_8 = "UTF-8";
    public static final String ON = "on";

    //general attributes
    public static final String PREV_URL = "prevURL";
    public static final String STRING_NULL = "null";

    //pageable
    public static final int FIRST_PAGE = 1;

    public static final int DEFAULT_PAGE_SIZE = 5;

    public static final String PAGE_NUMBER = "pageNumber";

    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGEABLE = "pageable";
    public static final String EMPTY_STRING = "";
    public static final String PARAM_PAGE = "coffee-manage?command=%s";
    public static final String PARAM_PAGE_WITH_FILTER = "coffee-manage?command=%s%s";
    public static final String PARAM_PAGE_WITH_SORT = "coffee-manage?command=%s%s%s";
    public static final String PARAM_SEARCH = "&%s=%s";
    public static final String PARAM_SORT = "&%s=%s";
    public static final String ATTR_PARAM_PAGE_URL = "paramUrl";
    public static final String ATTR_SORT_FIELD = "sortField";

    //role attributes
    public static final String ATTR_ROLE_NAME = "name";
    public static final String ATTR_SEARCH_ROLE_NAME = "searchName";
    public static final String ATTR_ROLE_ID = "roleId";
    public static final String ATTR_ROLE = "role";
    public static final String ATTR_ROLES = "roles";

    //users attributes
    public static final String ATTR_USERNAME = "username";
    public static final String ATTR_SEARCH_USERNAME = "searchUsername";
    public static final String ATTR_PASSWORD = "password";
    public static final String ATTR_OLD_PASSWORD = "oldPassword";
    public static final String ATTR_CHECK_ROLES = "checkRoles";
    public static final String ATTR_USER = "user";

    //customer attributes
    public static final String ATTR_CUSTOMER_ID = "customerId";
    public static final String ATTR_CUSTOMER = "customer";

    public static final String ATTR_CUSTOMER_NAME = "name";

    public static final String ATTR_CUSTOMER_SURNAME = "surname";

    public static final String ATTR_CUSTOMER_PHONE = "phone";

    public static final String ATTR_CUSTOMER_EMAIL = "email";
    public static final String ATTR_SEARCH_CUSTOMER_NAME = "searchName";

    public static final String ATTR_SEARCH_CUSTOMER_SURNAME = "searchSurname";

    public static final String ATTR_SEARCH_CUSTOMER_PHONE = "searchPhone";

    public static final String ATTR_SEARCH_CUSTOMER_EMAIL = "searchEmail";

    //credit cards attributes
    public static final String ATTR_LIST_CREDIT_CARDS = "creditCards";
    public static final String ATTR_CREDIT_CARD_NUMBER = "number";
    public static final String ATTR_CREDIT_CARD_ID = "creditCardId";
    public static final String ATTR_CREDIT_CARD = "creditCard";

    //addresses attributes
    public static final String ATTR_ADDRESS_CITY = "city";
    public static final String ATTR_SEARCH_ADDRESS_CITY = "searchCity";
    public static final String ATTR_ADDRESS_STREET = "street";
    public static final String ATTR_SEARCH_ADDRESS_STREET = "searchStreet";
    public static final String ATTR_ADDRESS_ID = "addressId";
    public static final String ATTR_ADDRESS = "address";
    public static final String ATTR_ADDRESSES = "addresses";

    //models machine attributes
    public static final String ATTR_MODEL_BRAND = "brand";
    public static final String ATTR_SEARCH_MODEL_BRAND = "searchBrand";
    public static final String ATTR_MODEL_NAME_MODEL = "nameModel";
    public static final String ATTR_SEARCH_MODEL_NAME_MODEL = "searchNameModel";
    public static final String ATTR_MODEL_ID = "modelId";
    public static final String ATTR_MODEL = "model";
    public static final String ATTR_MODELS = "models";

    //products attributes
    public static final String ATTR_PRODUCT_NAME = "name";
    public static final String ATTR_SEARCH_PRODUCT_NAME = "searchName";
    public static final String ATTR_PRODUCT_PRICE = "price";
    public static final String ATTR_SEARCH_PRODUCT_PRICE = "searchPrice";
    public static final String ATTR_LIST_PRODUCTS = "products";
    public static final String ATTR_PRODUCT_ID = "productId";
    public static final String ATTR_PRODUCT = "product";

    //machines attributes
    public static final String ATTR_MACHINE_S_N = "serialNumber";
    public static final String ATTR_SEARCH_MACHINE_S_N = "searchSerialNumber";
    public static final String ATTR_MACHINE_BRAND = "brand";
    public static final String ATTR_SEARCH_MACHINE_BRAND = "searchBrand";
    public static final String ATTR_MACHINE_NAME_MODEL = "nameModel";
    public static final String ATTR_SEARCH_MACHINE_NAME_MODEL = "searchNameModel";
    public static final String ATTR_MACHINE_CITY = "city";
    public static final String ATTR_SEARCH_MACHINE_CITY = "searchCity";
    public static final String ATTR_MACHINE_STREET = "street";
    public static final String ATTR_SEARCH_MACHINE_STREET = "searchStreet";
    public static final String ATTR_MACHINE_ID = "machineId";
    public static final String ATTR_MACHINE = "machine";
    public static final String ATTR_MACHINE_MODEL = "model";
    public static final String ATTR_MACHINE_ADDRESS = "address";
    public static final String PREV_URL_MACHINES = "prevUrlMachines";

    //discounts attributes
    public static final String ATTR_DISCOUNT_ID = "discountId";
    public static final String ATTR_DISCOUNT_PERCENT = "percent";
    public static final String ATTR_DISCOUNT_AMOUNT = "amount";
    public static final String ATTR_LIST_DISCOUNTS = "discounts";
    public static final String ATTR_DISCOUNT = "discount";

    //type payments attributes
    public static final String ATTR_TYPE_PAYMENT_ID = "typePaymentId";
    public static final String ATTR_TYPE_PAYMENT_NAME = "name";
    public static final String ATTR_TYPE_PAYMENT_USE_CARD = "useCreditCard";
    public static final String ATTR_TYPE_PAYMENT_USE_PHONE = "usePhoneNumber";
    public static final String ATTR_LIST_TYPE_PAYMENTS = "typePayments";
    public static final String ATTR_TYPE_PAYMENT = "typePayment";

    //purchases attributes
    public static final String ATTR_PURCHASE_ID = "purchaseId";
    public static final String ATTR_PURCHASE_PRICE = "price";
    public static final String ATTR_PURCHASE_AMOUNT = "amount";
    public static final String ATTR_CUSTOMERS = "customers";
    public static final String ATTR_MACHINES = "machines";
    public static final String ATTR_PURCHASE = "purchase";

    //order
    public static final String ATTR_LIST_MACHINES = "machines";

    //name commands
    public static final String COMMAND_MAIN = "main";
    public static final String COMMAND_LOGIN = "login";
    public static final String COMMAND_REG = "registration";
    public static final String COMMAND_LOGOUT = "logout";
    public static final String COMMAND_PROFILE = "profile";
    public static final String COMMAND_CHANGE_PASSWORD = "changePassword";
    public static final String COMMAND_ROLES = "roles";
    public static final String COMMAND_NEW_ROLE = "newRole";
    public static final String COMMAND_UPDATE_ROLE = "updateRole";
    public static final String COMMAND_DELETE_ROLE = "deleteRole";
    public static final String COMMAND_USERS = "users";
    public static final String COMMAND_NEW_USER = "newUser";
    public static final String COMMAND_EDIT_USER_PASS = "editUserPassword";
    public static final String COMMAND_EDIT_USER_ROLES = "editRolesUser";
    public static final String COMMAND_CREDIT_CARDS = "creditCards";
    public static final String COMMAND_NEW_CREDIT_CARD = "newCreditCard";
    public static final String COMMAND_EDIT_CREDIT_CARD = "updateCreditCard";
    public static final String COMMAND_DELETE_CREDIT_CARD = "deleteCreditCard";
    public static final String COMMAND_ADDRESSES = "addresses";
    public static final String COMMAND_NEW_ADDRESS = "newAddress";
    public static final String COMMAND_EDIT_ADDRESS = "updateAddress";
    public static final String COMMAND_DELETE_ADDRESS = "deleteAddress";
    public static final String COMMAND_MODELS = "models";
    public static final String COMMAND_NEW_MODEL = "newModel";
    public static final String COMMAND_EDIT_MODEL = "updateModel";
    public static final String COMMAND_DELETE_MODEL = "deleteModel";
    public static final String COMMAND_PRODUCTS = "products";
    public static final String COMMAND_NEW_PRODUCT = "newProduct";
    public static final String COMMAND_EDIT_PRODUCT = "updateProduct";
    public static final String COMMAND_DELETE_PRODUCT = "deleteProduct";
    public static final String COMMAND_CUSTOMERS = "customers";
    public static final String COMMAND_NEW_CUSTOMER = "newCustomer";
    public static final String COMMAND_EDIT_CUSTOMER = "updateCustomer";
    public static final String COMMAND_DELETE_CUSTOMER = "deleteCustomer";
    public static final String COMMAND_MACHINES = "machines";
    public static final String COMMAND_NEW_MACHINE = "newMachine";
    public static final String COMMAND_EDIT_MACHINE = "updateMachine";
    public static final String COMMAND_DELETE_MACHINE = "deleteMachine";
    public static final String COMMAND_MACHINE_PRODUCTS = "updateProductsMachine";
    public static final String COMMAND_NEW_MACHINE_PRODUCT = "newMachineProduct";
    public static final String COMMAND_DELETE_MACHINE_PRODUCT = "deleteMachineProduct";
    public static final String COMMAND_DISCOUNTS = "discounts";
    public static final String COMMAND_NEW_DISCOUNT = "newDiscount";
    public static final String COMMAND_EDIT_DISCOUNT = "updateDiscount";
    public static final String COMMAND_DELETE_DISCOUNT = "deleteDiscount";
    public static final String COMMAND_PAYMENTS = "typePayments";
    public static final String COMMAND_NEW_PAYMENT = "newTypePayment";
    public static final String COMMAND_EDIT_PAYMENT = "updateTypePayment";
    public static final String COMMAND_DELETE_PAYMENT = "deleteTypePayment";
    public static final String COMMAND_PURCHASES = "purchases";
    public static final String COMMAND_NEW_PURCHASE = "newPurchase";
    public static final String COMMAND_EDIT_PURCHASE = "updatePurchase";
    public static final String COMMAND_DELETE_PURCHASE = "deletePurchase";
    public static final String COMMAND_MAIN_ORDER = "mainOrder";
    public static final String COMMAND_CHOOSE_MACHINE = "chooseMachine";
    public static final String COMMAND_CHOOSE_PRODUCT = "chooseProduct";
    public static final String COMMAND_CHOOSE_TYPE_PAY = "chooseTypePay";
    public static final String COMMAND_NEW_ORDER_PAY = "newOrderPay";

    //error
    public static final String ATTR_MESSAGE = "message";
    public static final String ERROR_REQUEST = "Неизвестный тип запроса";
    public static final String ERROR_ACCESS = "Попытка запуска недоступной команды";
    public static final String ERROR_LOGIN = "Неверное имя пользователя или пароль";
    public static final String ERROR_REGISTRATION = "Ошибка регистрации";
    public static final String ERROR_UPDATE = "Ошибка обновления";
    public static final String ERROR_NEW = "Ошибка создания";
    public static final String ERROR_DELETE = "Ошибка удаления";
    public static final String AUTH_JSP = "login,registration,logout";

    //jsp page
    public static final String MAIN_JSP = "view/index.jsp";
    public static final String ERROR_JSP = "error.jsp";
    public static final String LOGIN_JSP = "login.jsp";
    public static final String REGISTRATION_JSP = "registration.jsp";
    public static final String PROFILE_JSP = "view/profile.jsp";
    public static final String CHANGE_PASSWORD_JSP = "view/changePassword.jsp";
    public static final String ROLES_JSP = "view/role/listRoles.jsp";
    public static final String NEW_ROLE_JSP = "view/role/addRole.jsp";
    public static final String EDIT_ROLE_JSP = "view/role/editRole.jsp";
    public static final String USERS_JSP = "view/user/listUsers.jsp";
    public static final String NEW_USER_JSP = "view/user/addUser.jsp";
    public static final String EDIT_PASS_USER_JSP = "view/user/editPassUser.jsp";
    public static final String EDIT_ROLES_USER_JSP = "view/user/editRolesUser.jsp";
    public static final String CREDIT_CARDS_JSP = "view/creditCard/listCreditCards.jsp";
    public static final String NEW_CREDIT_CARD_JSP = "view/creditCard/addCreditCard.jsp";
    public static final String EDIT_CREDIT_CARD_JSP = "view/creditCard/editCreditCard.jsp";
    public static final String ADDRESSES_JSP = "view/address/listAddresses.jsp";
    public static final String NEW_ADDRESS_JSP = "view/address/addAddress.jsp";
    public static final String EDIT_ADDRESS_JSP = "view/address/editAddress.jsp";
    public static final String MODELS_JSP = "view/model/listModels.jsp";
    public static final String NEW_MODEL_JSP = "view/model/addModel.jsp";
    public static final String EDIT_MODEL_JSP = "view/model/editModel.jsp";
    public static final String PRODUCTS_JSP = "view/product/listProducts.jsp";
    public static final String NEW_PRODUCT_JSP = "view/product/addProduct.jsp";
    public static final String EDIT_PRODUCT_JSP = "view/product/editProduct.jsp";
    public static final String CUSTOMERS_JSP = "view/customer/listCustomers.jsp";
    public static final String NEW_CUSTOMER_JSP = "view/customer/addCustomer.jsp";
    public static final String EDIT_CUSTOMER_JSP = "view/customer/editCustomer.jsp";
    public static final String MACHINES_JSP = "view/machine/listMachines.jsp";
    public static final String NEW_MACHINE_JSP = "view/machine/addMachine.jsp";
    public static final String EDIT_MACHINE_JSP = "view/machine/editMachine.jsp";
    public static final String MACHINE_PRODUCTS_JSP = "view/machine/listMachineProducts.jsp";
    public static final String NEW_MACHINE_PRODUCT_JSP = "view/machine/addMachineProduct.jsp";
    public static final String DISCOUNTS_JSP = "view/discount/listDiscounts.jsp";
    public static final String NEW_DISCOUNT_JSP = "view/discount/addDiscount.jsp";
    public static final String EDIT_DISCOUNT_JSP = "view/discount/editDiscount.jsp";
    public static final String PAYMENTS_JSP = "view/typePayment/listTypePayments.jsp";
    public static final String NEW_PAYMENT_JSP = "view/typePayment/addTypePayment.jsp";
    public static final String EDIT_PAYMENT_JSP = "view/typePayment/editTypePayment.jsp";
    public static final String PURCHASES_JSP = "view/purchase/listPurchases.jsp";
    public static final String NEW_PURCHASE_JSP = "view/purchase/addPurchase.jsp";
    public static final String EDIT_PURCHASE_JSP = "view/purchase/editPurchase.jsp";
    public static final String MAIN_ORDER_JSP = "view/order/mainOrder.jsp";
    public static final String ORDER_MACHINES_JSP = "view/order/listMachinesOrder.jsp";
    public static final String ORDER_PRODUCTS_JSP = "view/order/listProductsOrder.jsp";
    public static final String ORDER_TYPE_PAY_JSP = "view/order/typePayOrder.jsp";
}
