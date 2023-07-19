package it.academy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

import static it.academy.utils.constants.DataDB.ATTR_DB_CUSTOMER;
import static it.academy.utils.constants.DataDB.ATTR_DB_CUSTOMER_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_USER_ID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table
public class Customer extends DataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @EqualsAndHashCode.Include
    private String name;

    @Column
    @EqualsAndHashCode.Include
    private String surname;

    @Column
    @EqualsAndHashCode.Include
    private String phone;

    @Column
    @EqualsAndHashCode.Include
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = ATTR_DB_USER_ID)
    private User user;

    @OneToMany(mappedBy = ATTR_DB_CUSTOMER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CreditCard> creditCards;
}