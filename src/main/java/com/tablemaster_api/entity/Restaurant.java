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


    public static class Builder {
        private final String name;
        private final String address;
        private final String location;
        private final LocalTime workTimeOpen;
        private final LocalTime workTimeClosed;

        private String description;
        private String phone;
        private String email;
        private Double rating;
        private Integer countOfReviews = 0;
        private Set<Tag> tags = new HashSet<>();
        private List<Reservation> reservations = new ArrayList<>();
        private List<Review> reviews = new ArrayList<>();
        private List<DaysOfWeek> workDays = new ArrayList<>();

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder countOfReviews(Integer countOfReviews) {
            this.countOfReviews = countOfReviews;
            return this;
        }

        public Builder tags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder reservations(List<Reservation> reservations) {
            this.reservations = reservations;
            return this;
        }

        public Builder reviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder workDays(List<DaysOfWeek> workDays) {
            this.workDays = workDays;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }

        public Builder(String name, String address, String location, LocalTime workTimeOpen, LocalTime workTimeClosed) {
            this.name = name;
            this.address = address;
            this.location = location;
            this.workTimeOpen = workTimeOpen;
            this.workTimeClosed = workTimeClosed;
        }
    }

    public Restaurant() {
    }

    public Restaurant(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.location = builder.location;
        this.workTimeOpen = builder.workTimeOpen;
        this.workTimeClosed = builder.workTimeClosed;
        this.description = builder.description;
        this.phone = builder.phone;
        this.email = builder.email;
        this.rating = builder.rating;
        this.countOfReviews = builder.countOfReviews;
        this.tags = builder.tags;
        this.reservations = builder.reservations;
        this.reviews = builder.reviews;
        this.workDays = builder.workDays;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getCountOfReviews() {
        return countOfReviews;
    }

    public LocalTime getWorkTimeOpen() {
        return workTimeOpen;
    }

    public LocalTime getWorkTimeClosed() {
        return workTimeClosed;
    }

    public List<DaysOfWeek> getWorkDays() {
        return workDays;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Review> getReviews() {
        return reviews;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", countOfReviews=" + countOfReviews +
                ", workTimeOpen=" + workTimeOpen +
                ", workTimeClosed=" + workTimeClosed +
                '}';
    }
}
