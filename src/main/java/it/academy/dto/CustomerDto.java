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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class CustomerDto extends DataEntity implements Serializable {
    private Integer id;

    private String name;

    private String surname;

    private String phone;

    private String email;

    @ToString.Exclude
    private UserDto user;

    @ToString.Exclude
    private Set<CreditCardDto> creditCards;
}