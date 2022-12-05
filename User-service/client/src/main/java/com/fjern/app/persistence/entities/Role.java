package com.fjern.app.persistence.entities;

import com.fjern.common.interfaces.NameableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="roles")
public class Role implements NameableEntity {

    public Role() {
        super();
        this.privileges=new HashSet<>();
    }

    public Role(String name) {
        super();
        this.name = name;
        this.privileges = new HashSet<>();
    }

    public Role(String name, Set<Privilege> privileges) {
        super();
        this.name = name;
        this.privileges = privileges;
    }

    public Role(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
        this.privileges = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName = "ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "PRIVILEGE_ID")})
    private Set<Privilege> privileges;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
