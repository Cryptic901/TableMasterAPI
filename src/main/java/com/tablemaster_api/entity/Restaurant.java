package com.tablemaster_api.entity;

import com.tablemaster_api.enums.DaysOfWeek;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;

    private String email;
    @Column(nullable = false)
    private String location;

    private Double rating;

    @Column(name = "count_of_reviews")
    private Integer countOfReviews = 0;

    @Column(name = "work_time_open", nullable = false)
    private LocalTime workTimeOpen;

    @Column(name = "work_time_closed", nullable = false)
    private LocalTime workTimeClosed;

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private List<DaysOfWeek> workDays = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "restaurant_tags",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();


    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String description, String address, String phone,
                      String email, String location, Double rating, Integer countOfReviews,
                      LocalTime workTimeOpen, LocalTime workTimeClosed, List<DaysOfWeek> workDays,
                      Set<Tag> tags, List<Reservation> reservations, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.rating = rating;
        this.countOfReviews = countOfReviews;
        this.workTimeOpen = workTimeOpen;
        this.workTimeClosed = workTimeClosed;
        this.workDays = workDays;
        this.tags = tags;
        this.reservations = reservations;
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(location, that.location) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(countOfReviews, that.countOfReviews) &&
                Objects.equals(workTimeOpen, that.workTimeOpen) &&
                Objects.equals(workTimeClosed, that.workTimeClosed) &&
                Objects.equals(workDays, that.workDays) &&
                Objects.equals(tags, that.tags) &&
                Objects.equals(reservations, that.reservations) &&
                Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, address, phone, email, location,
                rating, countOfReviews, workTimeOpen, workTimeClosed, workDays, tags,
                reservations, reviews);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "workTimeClosed=" + workTimeClosed +
                ", workTimeOpen=" + workTimeOpen +
                ", countOfReviews=" + countOfReviews +
                ", rating=" + rating +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
