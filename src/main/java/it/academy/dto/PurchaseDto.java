package it.academy.dto;

import it.academy.models.DataEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto extends DataEntity implements Serializable {
    private Integer id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CustomerDto customer;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MachineDto machine;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductDto product;

    private Float price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DiscountDto discount;

    private Float amount;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CreditCardDto creditCard;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TypePaymentDto typePayment;

    private LocalDateTime createDate;
}
