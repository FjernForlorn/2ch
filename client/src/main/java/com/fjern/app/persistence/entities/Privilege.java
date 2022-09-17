package com.fjern.app.persistence.entities;

import com.fjern.common.interfaces.NameableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "privileges")
public class Privilege implements NameableEntity {

    public Privilege() {
        super();
    }

    public Privilege(String name) {
        super();
        this.name = name;
    }

    public Privilege(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIVILEGE_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Privilege)) return false;
        Privilege privilege = (Privilege) o;
        return name.equals(privilege.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
