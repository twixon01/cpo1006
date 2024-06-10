package com.example.airlinesBuy.Entity;

import javax.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "from_station_id", nullable = false)
    private Station fromStation;

    @ManyToOne
    @JoinColumn(name = "to_station_id", nullable = false)
    private Station toStation;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column(name = "created", nullable = false, updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Timestamp created;
}

