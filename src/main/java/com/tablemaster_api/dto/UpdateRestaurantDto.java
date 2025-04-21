package com.tablemaster_api.dto;

import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Tag;
import com.tablemaster_api.enums.DaysOfWeek;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UpdateRestaurantDto {
    private String name;
    private String description;
    private String address;
    private String location;
    private Double rating;
    private Integer countOfReviews;
    private LocalTime workTimeOpen;
    private LocalTime workTimeClosed;
    private List<DaysOfWeek> workDays;
    private Set<Tag> tags;
    private List<Reservation> reservations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getCountOfReviews() {
        return countOfReviews;
    }

    public void setCountOfReviews(Integer countOfReviews) {
        this.countOfReviews = countOfReviews;
    }

    public LocalTime getWorkTimeOpen() {
        return workTimeOpen;
    }

    public void setWorkTimeOpen(LocalTime workTimeOpen) {
        this.workTimeOpen = workTimeOpen;
    }

    public LocalTime getWorkTimeClosed() {
        return workTimeClosed;
    }

    public void setWorkTimeClosed(LocalTime workTimeClosed) {
        this.workTimeClosed = workTimeClosed;
    }

    public List<DaysOfWeek> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<DaysOfWeek> workDays) {
        this.workDays = workDays;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public UpdateRestaurantDto() {}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRestaurantDto that = (UpdateRestaurantDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(address, that.address) &&
                Objects.equals(location, that.location) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(countOfReviews, that.countOfReviews) &&
                Objects.equals(workTimeOpen, that.workTimeOpen) &&
                Objects.equals(workTimeClosed, that.workTimeClosed) &&
                Objects.equals(workDays, that.workDays) &&
                Objects.equals(tags, that.tags) &&
                Objects.equals(reservations, that.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, address, location, rating, countOfReviews, workTimeOpen,
                workTimeClosed, workDays, tags, reservations);
    }

    @Override
    public String toString() {
        return "UpdateRestaurantDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", countOfReviews=" + countOfReviews +
                ", workTimeOpen=" + workTimeOpen +
                ", workTimeClosed=" + workTimeClosed +
                '}';
    }
}
