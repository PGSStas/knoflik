package com.knoflik.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public final class User {
    private final int columnLength = 32;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @Column(length = columnLength)
    private String id;

    @Column(nullable = false, length = columnLength, name = "username")
    private String username;

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

    @Override
    public String toString() {
        return "[id = " + getId() + ", username = " + getUsername() + "]";
    }
}
