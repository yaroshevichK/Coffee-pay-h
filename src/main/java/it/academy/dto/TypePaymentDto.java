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

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TypePaymentDto extends DataEntity implements Serializable {
    private Integer id;

    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;

    private Boolean useCreditCard;

    private Boolean usePhoneNumber;
}
