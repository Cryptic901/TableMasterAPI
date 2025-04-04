package com.tablemaster_api.entity;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private Tables tables;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "reservation_time_start")
    private LocalDateTime reservationTimeStart;

    @Column(name = "reservation_time_end")
    private LocalDateTime reservationTimeEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    public Reservation() {
    }

    public Reservation(Long id, User user, Tables tables, Restaurant restaurant, LocalDateTime reservationTimeStart, LocalDateTime reservationTimeEnd) {
        this.id = id;
        this.user = user;
        this.tables = tables;
        this.restaurant = restaurant;
        this.reservationTimeStart = reservationTimeStart;
        this.reservationTimeEnd = reservationTimeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(tables, that.tables) && Objects.equals(restaurant, that.restaurant) && Objects.equals(reservationTimeStart, that.reservationTimeStart) && Objects.equals(reservationTimeEnd, that.reservationTimeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, tables, restaurant, reservationTimeStart, reservationTimeEnd);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", tables=" + tables +
                ", restaurant=" + restaurant +
                ", reservationTimeStart=" + reservationTimeStart +
                ", reservationTimeEnd=" + reservationTimeEnd +
                '}';
    }
}
