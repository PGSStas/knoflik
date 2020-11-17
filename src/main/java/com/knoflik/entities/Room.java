package com.knoflik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "pack_id", length = columnLength)
    private String packID = null;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public String getPackID() {
        return packID;
    }

    public void setPackID(final String newPackID) {
        this.packID = newPackID;
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

    @Override
    public String toString() {
        return "[id = " + getId() + ", Admin = " + getAdmin()
                + ", PackID = " + getPackID() + ", UsersInRoom = "
                + activeUsers.toString();
    }
}
