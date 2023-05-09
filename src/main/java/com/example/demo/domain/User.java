package com.example.demo.domain;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@Entity
@Table(name = "t_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String username;
  private String password;
  private String email;
  private String avatar;

  @ColumnDefault("user")
  private String role;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Project.class)
  @JoinColumn(name = "owner_id")
  private List<Project> projectList;

}
