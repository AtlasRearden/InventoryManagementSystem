package com.inventoryManagement.entities;


import com.inventoryManagement.auth.Role;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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
    @Column(name="role")
    private String role;

    private Boolean isAdmin;

    public User(Long user_id, String name, String email) {}

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Cart> carts;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="admin_id",referencedColumnName = "admin_id")
//    private Admin admin;


}