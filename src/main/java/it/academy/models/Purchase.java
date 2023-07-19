package it.academy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

import static it.academy.utils.constants.DataDB.ATTR_DB_CREDIT_CARD_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_DISCOUNT_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_MACHINE_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_PRODUCT_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_TYPE_PAYMENT_ID;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Purchase extends DataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = ATTR_DB_MACHINE_ID)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = ATTR_DB_PRODUCT_ID)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;

    @Column
    private Float price;

    @ManyToOne
    @JoinColumn(name = ATTR_DB_DISCOUNT_ID)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Discount discount;

    @Column
    private Float amount;

    @ManyToOne
    @JoinColumn(name = ATTR_DB_CREDIT_CARD_ID)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = ATTR_DB_TYPE_PAYMENT_ID)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TypePayment typePayment;
}
