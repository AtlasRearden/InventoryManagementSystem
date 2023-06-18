package com.inventoryManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long cart_id;
    @Column(name="quantity")
    private double quantity;
    @Column(name="cart_number", unique = true)
    private Long cart_number;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart_item",
            joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "cart_id"),
                    @JoinColumn(name = "quantity", referencedColumnName = "quantity")},
                    inverseJoinColumns=@JoinColumn(name = "item_id", referencedColumnName = "item_id")
    )
    private List<Item> items ;

    @OneToOne(mappedBy = "cart",cascade = CascadeType.ALL)
    private PaymentMethod paymentMethod;
}