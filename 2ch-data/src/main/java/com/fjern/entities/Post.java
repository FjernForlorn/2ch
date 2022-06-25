package com.fjern.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post extends BaseEntity{

    @Column(name = "text")
    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "parents_children",
            joinColumns = {@JoinColumn(name="child_id")},
            inverseJoinColumns = {@JoinColumn(name = "parent_id")}
    )
    private List<Post> parents;

    @JsonIgnore
    @ManyToMany(mappedBy = "parents", fetch = FetchType.LAZY)
    private List<Post> children;



    public Post(String text){
        this.text=text;


    }

    public void addParent(Post post){
        if (this.parents==null){
            this.parents=new ArrayList<>();
        }
        this.parents.add(post);
        //post.addChild(this);
    }

    private void addChild(Post post){
        if (this.children==null){
            this.children=new ArrayList<>();
        }
        this.children.add(post);
    }

}
