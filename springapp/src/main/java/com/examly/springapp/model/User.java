package com.examly.springapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String userRole;
    @Transient
    private String token;

    public User() {
    }

    public User(String email, String password, String username, String mobileNumber, String userRole) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }

    // public User(Long userId, String email, String password, String username, String mobileNumber, String userRole,
    //         String token, List<Booking> bookings) {
    //     this.userId = userId;
    //     this.email = email;
    //     this.password = password;
    //     this.username = username;
    //     this.mobileNumber = mobileNumber;
    //     this.userRole = userRole;
    //     this.token = token;
    //     this.bookings = bookings;
    // }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // public List<Booking> getBookings() {
    //     return bookings;
    // }

    // public void setBookings(List<Booking> bookings) {
    //     this.bookings = bookings;
    // }

}

