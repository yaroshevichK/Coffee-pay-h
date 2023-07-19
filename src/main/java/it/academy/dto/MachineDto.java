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
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MachineDto extends DataEntity implements Serializable {
    private Integer id;

    @EqualsAndHashCode.Include
    private String serialNumber;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AddressDto address;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ModelDto model;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ProductDto> products;
}
