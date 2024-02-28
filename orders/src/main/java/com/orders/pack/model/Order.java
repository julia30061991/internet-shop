package com.orders.pack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", columnDefinition = "INT", unique = true)
    private int orderId;
    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @Override
    public String toString() {
        return "com.common.all.model.Order{" +
                "id=" + orderId +
                ", name='" + name + '\'' +
                ", buyer=" + buyer +
                '}';
    }
}