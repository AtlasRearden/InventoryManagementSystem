package com.inventoryManagement.entities;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long user_id;
    @Column(name="name")
    private String name;
    @Column(name="phone_number")
    private String phone_number;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    private String roles;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Cart> carts;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="admin_id",referencedColumnName = "admin_id")
    private Admin admin;


}