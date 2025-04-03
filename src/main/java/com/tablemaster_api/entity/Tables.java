package com.tablemaster_api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tables")
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_time")
    private LocalDateTime reservationTime;

    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private boolean reserved = false;

    public Tables(Long id, LocalDateTime reservationTime, Integer capacity, Restaurant restaurant, boolean reserved) {
        this.id = id;
        this.reservationTime = reservationTime;
        this.capacity = capacity;
        this.restaurant = restaurant;
        this.reserved = reserved;
    }

    public Tables() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tables tables = (Tables) o;
        return Objects.equals(id, tables.id) &&
                Objects.equals(reservationTime, tables.reservationTime) &&
                Objects.equals(capacity, tables.capacity) &&
                Objects.equals(restaurant, tables.restaurant) &&
                Objects.equals(reserved, tables.reserved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservationTime, capacity, restaurant, reserved);
    }

    @Override
    public String toString() {
        return "Tables{" +
                "id=" + id +
                ", reservationTime='" + reservationTime + '\'' +
                ", capacity=" + capacity +
                ", restaurant=" + restaurant +
                ", reserved=" + reserved +
                '}';
    }
}
