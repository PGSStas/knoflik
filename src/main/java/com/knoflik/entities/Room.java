package com.knoflik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @Column(name = "pack_id", length = columnLength)
    private String packID = null;

    @OneToMany(mappedBy = "currentRoom", fetch = FetchType.LAZY)
    private Set<User> activeUsers;

    public String getId() {
        return id;
    }

    public void setId(final String newId) {
        this.id = newId;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(final String newPackID) {
        this.packID = newPackID;
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
        return "[id = " + getId() + ", PackID = " + getPackID()
                + ", UsersInRoom = " + activeUsers.toString();
    }
}
