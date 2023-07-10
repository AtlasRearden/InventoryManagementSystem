//package com.inventoryManagement.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.inventoryManagement.auth.Role;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name="admin")
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//public class Admin{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="admin_id")
//    private Long admin_id;
//    @Column(name="email")
//    private String email;
//    @Column(name="password")
//    private String password;
//
//    private String roles;
//
//    @OneToMany
//    private List<Item> items;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
//    private List<User>users;
//
//}
