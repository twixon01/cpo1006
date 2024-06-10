package com.example.airlinesBuy.Entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "station", nullable = false, length = 50)
    private String station;
}

