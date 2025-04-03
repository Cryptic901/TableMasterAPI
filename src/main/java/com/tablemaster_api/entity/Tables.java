package com.tablemaster_api.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tables")
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_time")
    private String reservationTimeStart;

    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Tables(Long id, String reservationTimeStart, Integer capacity, Restaurant restaurant) {
        this.id = id;
        this.reservationTimeStart = reservationTimeStart;
        this.capacity = capacity;
        this.restaurant = restaurant;
    }

    public Tables() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationTimeStart() {
        return reservationTimeStart;
    }

    public void setReservationTimeStart(String reservationTimeStart) {
        this.reservationTimeStart = reservationTimeStart;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tables tables = (Tables) o;
        return Objects.equals(id, tables.id) &&
                Objects.equals(reservationTimeStart, tables.reservationTimeStart) &&
                Objects.equals(capacity, tables.capacity) &&
                Objects.equals(restaurant, tables.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservationTimeStart, capacity, restaurant);
    }

    @Override
    public String toString() {
        return "Tables{" +
                "id=" + id +
                ", reservationTimeStart='" + reservationTimeStart + '\'' +
                ", capacity=" + capacity +
                ", restaurant=" + restaurant +
                '}';
    }
}
