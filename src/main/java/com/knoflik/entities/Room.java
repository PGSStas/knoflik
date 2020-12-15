package com.knoflik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    private String id;

    @OneToMany(mappedBy = "currentRoom", fetch = FetchType.LAZY)
    private Set<User> activeUsers;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    private RoomSettings settings;

    @OneToOne(mappedBy = "administratedRoom", fetch = FetchType.LAZY)
    private User admin;

    public RoomSettings getSettings() {
        return settings;
    }

    public void setSettings(final RoomSettings settings) {
        this.settings = settings;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(final User admin) {
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(final String newId) {
        this.id = newId;
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

    public void removeUserByUsername(final String nickname) {
        this.activeUsers.removeIf(a -> {
            return a.getUsername().equals(nickname);
        });
    }

    @Override
    public String toString() {
        return "Room{" + "id='" + id + '\'' + ", activeUsers=" + activeUsers
                + ", settings=" + settings + ", admin=" + admin + '}';
    }
}
