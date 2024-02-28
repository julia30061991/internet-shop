package com.orders.pack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_buyer")
public class Buyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "buyer_id", columnDefinition = "INT", unique = true)
    private int buyerId;
    @Column(name = "full_name", columnDefinition = "VARCHAR(100)")
    private String fullName;
    @Column(name = "phone_number", columnDefinition = "VARCHAR(40)")
    private String phoneNumber;
    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String address;
    @OneToMany(mappedBy = "buyer")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public Buyer(String fullName, String phoneNumber, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + buyerId +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}