package it.academy.utils.constants;

import it.academy.models.Address;
import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.models.Discount;
import it.academy.models.Machine;
import it.academy.models.Model;
import it.academy.models.Product;
import it.academy.models.Purchase;
import it.academy.models.Role;
import it.academy.models.TypePayment;
import it.academy.models.User;

public class DataGeneral {
    public static final String PERSISTENCE_NAME = "dataConnection";
    //roles
    public static final String ROLE_CUSTOMER = "Customer";
    //classes
    public static final Class<Long> LONG_CLASS = Long.class;
    public static final Class<Role> ROLE_CLASS = Role.class;
    public static final Class<User> USER_CLASS = User.class;
    public static final Class<Customer> CUSTOMER_CLASS = Customer.class;
    public static final Class<CreditCard> CREDIT_CARD_CLASS = CreditCard.class;
    public static final Class<Address> ADDRESS_CLASS = Address.class;
    public static final Class<Model> MODEL_CLASS = Model.class;
    public static final Class<Product> PRODUCT_CLASS = Product.class;
    public static final Class<Machine> MACHINE_CLASS = Machine.class;
    public static final Class<Discount> DISCOUNT_CLASS = Discount.class;
    public static final Class<TypePayment> TYPE_PAYMENT_CLASS = TypePayment.class;
    public static final Class<Purchase> PURCHASE_CLASS = Purchase.class;
}
