package com.knoflik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Transient
    private final int columnLength = 32;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false, length = columnLength, name = "username")
    private String username;

    private String password;

    private String currentRoomId;

    private boolean isAdmin = false;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(final String newId) {
        this.id = newId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(final String newPasswordConfirm) {
        this.passwordConfirm = newPasswordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> newRoles) {
        this.roles = newRoles;
    }

    public String getCurrentRoomId() {
        return currentRoomId;
    }

    public void setCurrentRoomId(final String currentRoomId) {
        this.currentRoomId = currentRoomId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(final boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "[id = " + getId() + ", username = " + getUsername()
                + ", password = " + getPassword() + ", passwordConfirm = "
                + getPasswordConfirm() + ", room = " + getCurrentRoomId() + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
