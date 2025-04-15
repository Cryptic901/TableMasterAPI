package com.tablemaster_api.entity;

import com.tablemaster_api.enums.Roles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @Column(name = "verification_code")
    private Integer verificationCode;

    @Column(name = "verification_expires")
    private LocalTime verificationCodeExpiresAt;

    @Column(name = "enabled")
    private boolean enabled;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    private Collection<Roles> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public LocalTime getVerificationCodeExpiresAt() {
        return verificationCodeExpiresAt;
    }

    public void setVerificationCodeExpiresAt(LocalTime verificationCodeExpiresAt) {
        this.verificationCodeExpiresAt = verificationCodeExpiresAt;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Roles> roles) {
        this.roles = roles;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
    }



    public User(Long id, String username, String email, String password,
                Integer verificationCode, LocalTime verificationCodeExpiresAt, boolean enabled, Collection<Roles> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
        this.verificationCodeExpiresAt = verificationCodeExpiresAt;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(String username, String email, String password, Collection<Roles> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(verificationCode, user.verificationCode) &&
                Objects.equals(verificationCodeExpiresAt, user.verificationCodeExpiresAt) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, verificationCode, verificationCodeExpiresAt, enabled, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", verificationCodeExpiresAt='" + verificationCodeExpiresAt + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
