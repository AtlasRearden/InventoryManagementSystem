package com.inventoryManagement.entities;

import jakarta.persistence.*;
import lombok.*;



import java.util.List;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long item_id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private Double price;

    @ManyToMany(mappedBy = "items")
    private List<Cart> cart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;

}
