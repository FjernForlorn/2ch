package com.fjern.app.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fjern.common.interfaces.IEntity;
import com.fjern.common.interfaces.NameableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements IEntity, NameableEntity {

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public User(String name, String email, String password, Boolean isEnabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = new HashSet<>();
    }

    public User(String name, String email, String password, Boolean isEnabled, HashSet<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name")
    private String name;

    @NotEmpty(message = "Email cannot be empty.")
    @Email
    @Column(name = "user_email",unique = true)
    private String email;

    @Column(name = "user_password")
    private String password;

    @JsonIgnore
    @Column(name = "is_enabled")
    private Boolean isEnabled = false;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "users2roles"
            ,joinColumns = {@JoinColumn(name="user_Id")}
            ,inverseJoinColumns = {@JoinColumn(name="role_Id")}
    )
    private Set<Role> roles;



    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
