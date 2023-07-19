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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserDto extends DataEntity implements Serializable {
    @EqualsAndHashCode.Include
    private String username;

    @ToString.Exclude
    private Set<RoleDto> roles;
}
