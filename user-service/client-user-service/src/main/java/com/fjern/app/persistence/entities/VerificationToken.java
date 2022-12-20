package com.fjern.app.persistence.entities;

import com.fjern.common.interfaces.NameableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class VerificationToken implements NameableEntity {

    public VerificationToken(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    @Column(name = "token_value", nullable = false)
    private String name;

    @Column(name= "expiry_date")
    private Date expiryDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id", nullable = false)
    private User user;
}
