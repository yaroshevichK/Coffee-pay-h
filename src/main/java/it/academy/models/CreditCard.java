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

import static it.academy.utils.constants.DataDB.ATTR_DB_CUSTOMER_ID;
import static it.academy.utils.constants.DataDB.TABLE_DB_CREDIT_CARD;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = TABLE_DB_CREDIT_CARD)
public class CreditCard extends DataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String number;

    @ManyToOne
    @JoinColumn(name = ATTR_DB_CUSTOMER_ID)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;
}
