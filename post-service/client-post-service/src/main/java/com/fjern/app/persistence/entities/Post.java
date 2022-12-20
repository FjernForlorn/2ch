package com.fjern.app.persistence.entities;

import com.fjern.common.interfaces.NameableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post implements NameableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_id")
    private Long userId;

    public Post(String text){
        this.text=text;

    }

}
