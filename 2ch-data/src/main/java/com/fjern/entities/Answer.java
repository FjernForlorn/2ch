//package com.fjern.entities;
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@IdClass(Answer.class)
//@Table(name = "answers")
//@Getter
//@Setter
//@EqualsAndHashCode
//public class Answer implements Serializable {
//
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id", updatable = false)
//    private Post parent;
//
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "child_id", updatable = false)
//    private Post child;
//
//
//}
