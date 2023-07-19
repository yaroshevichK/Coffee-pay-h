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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

import static it.academy.utils.constants.DataDB.ATTR_DB_ADDRESS_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_MACHINE_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_MODEL_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_PRODUCT_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_SERIAL_NUMBER;
import static it.academy.utils.constants.DataDB.DB_MACHINE_PRODUCT;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Machine extends DataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ATTR_DB_SERIAL_NUMBER)
    @EqualsAndHashCode.Include
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = ATTR_DB_ADDRESS_ID)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Address address;

    @ManyToOne
    @JoinColumn(name = ATTR_DB_MODEL_ID)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Model model;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = DB_MACHINE_PRODUCT,
            joinColumns = {@JoinColumn(name = ATTR_DB_MACHINE_ID)},
            inverseJoinColumns = {@JoinColumn(name = ATTR_DB_PRODUCT_ID)})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Product> products;
}
