package com.carolineeklund.architecture.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    private int size;
    private int cost;
    private boolean readyMade;

    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;



}
