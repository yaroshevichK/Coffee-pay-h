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
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import java.io.Serializable;

import static it.academy.utils.constants.DataQuery.FIND_ROLE_BY_NAME;
import static it.academy.utils.constants.DataQuery.QUERY_FIND_BY_NAME;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@NamedNativeQuery(name = FIND_ROLE_BY_NAME, query = QUERY_FIND_BY_NAME, resultClass = Role.class)
public class Role extends DataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @EqualsAndHashCode.Include
    private String name;
}
