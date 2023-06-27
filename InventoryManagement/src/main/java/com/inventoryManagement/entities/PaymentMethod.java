package com.inventoryManagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentMethod{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long payment_id;
    @Column(name="total_price")
    private BigDecimal total_price;
    @Column(name="payment_date")
    private Date payment_date;

    private int cart_number;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

}
