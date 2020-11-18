package com.knoflik.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    private final int columnLength = 32;

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private RoomSettings settings;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private User admin;

    @OneToMany(fetch = FetchType.LAZY)
    @MapsId
    private Set<User> activeUsers;

    public String getId() {
        return id;
    }

    public void setId(final String newId) {
        this.id = newId;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(final User newAdmin) {
        this.admin = newAdmin;
    }

    public Set<User> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(final Set<User> newActiveUsers) {
        this.activeUsers = newActiveUsers;
    }

    public void addUser(final User user) {
        this.activeUsers.add(user);
    }

    public RoomSettings getSettings() {
        return settings;
    }

    public void setSettings(RoomSettings settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return "Room{" +
               "id='" + id + '\'' +
               ", settings=" + settings +
               ", admin=" + admin +
               ", activeUsers=" + activeUsers +
               '}';
    }
}
