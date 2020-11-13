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
public final class Room {
    private final int columnLength = 32;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @Column(length = length)
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private User admin;

    @Column(name = "pack_id", length = columnLength)
    private String packID = null;

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
        if (admin == null) {
            System.out.println(user);
            setAdmin(user);
        }
        this.activeUsers.add(user);
    }

    @Override
    public String toString() {
        return "[id = " + getId() + ", Admin = " + getAdmin()
                + ", PackID = " + getPackID() + ", UsersInRoom = "
                + activeUsers.toString();
    }
}
