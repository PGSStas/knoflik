package com.knoflik.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "admin_id", length = 16)
    private String adminID;

    @Column(name = "pack_id", length = 32)
    private String packID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    @Override
    public String toString() {
        return "[id = " + getId() + ", AdminID = " + getAdminID() + ", PackID = " + getPackID() + "]";
    }
}
