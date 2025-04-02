package com.tablemaster_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
