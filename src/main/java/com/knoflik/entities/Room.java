package com.knoflik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "rooms")
public class Room {
    private final int columnLength = 32;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToMany(mappedBy = "currentRoom", fetch = FetchType.LAZY)
    private Set<User> activeUsers;

    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY, optional = false)
    private RoomSettings settings;

    @OneToOne(mappedBy = "administratedRoom", fetch = FetchType.LAZY, optional = false)
    private User admin;

    public RoomSettings getSettings() {
        return settings;
    }

    public void setSettings(RoomSettings settings) {
        this.settings = settings;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
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
        return "Room{" +
               "id='" + id + '\'' +
               ", activeUsers=" + activeUsers +
               ", settings=" + settings +
               ", admin=" + admin +
               '}';
    }
}
