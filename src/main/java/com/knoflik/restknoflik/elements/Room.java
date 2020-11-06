package com.knoflik.restknoflik.elements;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@TableGenerator(name = "Room")
public class Room {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(6)")
    private String id;

    private Long admin_id;
    private Long pack_id;
    private Long current_question;


    public Room() {
    }
}
