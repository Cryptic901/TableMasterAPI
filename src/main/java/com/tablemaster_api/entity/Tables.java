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

    @Column(name = "reservation_time_start")
    private LocalDateTime reservationTimeStart;

    @Column(name = "reservation_time_end")
    private LocalDateTime reservationTimeEnd;

    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Tables(Long id, LocalDateTime reservationTimeStart,
                  LocalDateTime reservationTimeEnd, Integer capacity, Restaurant restaurant) {
        this.id = id;
        this.reservationTimeStart = reservationTimeStart;
        this.reservationTimeEnd = reservationTimeEnd;
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

    public LocalDateTime getReservationTimeStart() {
        return reservationTimeStart;
    }

    public void setReservationTimeStart(LocalDateTime reservationTimeStart) {
        this.reservationTimeStart = reservationTimeStart;
    }

    public LocalDateTime getReservationTimeEnd() {
        return reservationTimeEnd;
    }

    public void setReservationTimeEnd(LocalDateTime reservationTimeEnd) {
        this.reservationTimeEnd = reservationTimeEnd;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tables tables = (Tables) o;
        return Objects.equals(id, tables.id) && Objects.equals(reservationTimeStart, tables.reservationTimeStart) && Objects.equals(reservationTimeEnd, tables.reservationTimeEnd) && Objects.equals(capacity, tables.capacity) && Objects.equals(restaurant, tables.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservationTimeStart, reservationTimeEnd, capacity, restaurant);
    }

    @Override
    public String toString() {
        return "Tables{" +
                "id=" + id +
                ", reservationTimeStart=" + reservationTimeStart +
                ", reservationTimeEnd=" + reservationTimeEnd +
                ", capacity=" + capacity +
                ", restaurant=" + restaurant +
                '}';
    }
}
