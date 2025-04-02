package com.tablemaster_api.entity;

import com.tablemaster_api.DaysOfWeek;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    private Long id;

    private String name;

    private String description;

    private String address;

    private String phone;

    private String email;

    private String location;

    private Double rating;

    @Column(name = "count_of_reviews")
    private Integer countOfReviews;

    @Column(name = "work_time_open")
    private Timestamp workTimeOpen;

    @Column(name = "work_time_closed")
    private Timestamp workTimeClosed;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<DaysOfWeek> workDays;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tag> tags;
}
