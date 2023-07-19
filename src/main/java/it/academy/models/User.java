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
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

import static it.academy.utils.constants.DataDB.ATTR_DB_ROLE_ID;
import static it.academy.utils.constants.DataDB.ATTR_DB_USER_ID;
import static it.academy.utils.constants.DataDB.DB_USER_ROLE;
import static it.academy.utils.constants.DataDB.TABLE_DB_USERS;
import static it.academy.utils.constants.DataQuery.FIND_USER_BY_USERNAME;
import static it.academy.utils.constants.DataQuery.QUERY_FIND_BY_USERNAME;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = TABLE_DB_USERS)
@NamedNativeQuery(name = FIND_USER_BY_USERNAME, query = QUERY_FIND_BY_USERNAME, resultClass = User.class)
public class User extends DataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @EqualsAndHashCode.Include
    private String username;

    @Column
    private String password;

    @Column
    private String salt;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(name = DB_USER_ROLE,
            joinColumns = {@JoinColumn(name = ATTR_DB_USER_ID)},
            inverseJoinColumns = {@JoinColumn(name = ATTR_DB_ROLE_ID)})
    private Set<Role> roles;
}
