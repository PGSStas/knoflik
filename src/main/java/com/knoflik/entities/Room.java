package com.knoflik.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private User admin;

    @Column(name = "pack_id", length = 32)
    private String packID = null;

    @OneToMany(fetch = FetchType.LAZY)
    @MapsId
    private Set<User> activeUsers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public Set<User> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Set<User> activeUsers) {
        this.activeUsers = activeUsers;
    }

    public void addUser(User user) {
        if (admin == null) {
            System.out.println(user);
            setAdmin(user);
        }
        this.activeUsers.add(user);
    }

    @Override
    public String toString() {
        return "[id = " + getId() + ", Admin = " + getAdmin() + ", PackID = " + getPackID() + ", UsersInRoom = " + activeUsers.toString();
    }
}
