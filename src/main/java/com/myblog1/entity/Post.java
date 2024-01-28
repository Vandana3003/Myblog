package com.myblog1.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
       name="post" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}

)
@Setter
@Getter
public class Post {

           @Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
           private long id;

           private String title;
           private String description;
           private String content;


           @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL,orphanRemoval = true)
           List<Comment> comments = new ArrayList<>();

}
