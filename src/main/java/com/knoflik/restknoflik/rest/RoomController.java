package com.knoflik.restknoflik.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final HashSet<String> rooms = new HashSet<>();
    private final HashMap<String, Integer> usersInRooms = new HashMap<>();
    private final Random rnd = new Random();
    private final SimpMessagingTemplate template;

    @Autowired
    public RoomController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @GetMapping
    public HashSet<String> getRooms() {
        return rooms;
    }

    @GetMapping("/new")
    public String createRoom() {
        AtomicInteger roomNumber = new AtomicInteger();
        do {
            roomNumber.set(rnd.nextInt(1000000));
        } while (rooms.contains(Integer.toString(roomNumber.get())));
        String s = Integer.toString(roomNumber.get());
        rooms.add(s);
        usersInRooms.put(s, 0);
        return s;
    }

    @GetMapping("/{id}")
    public String getRoom(@PathVariable String id) {
        if (rooms.contains(id)) {
            return "200";
        } else {
            return "404";
        }
    }

    @PostMapping("/{id}/addUser")
    public void addUser(@PathVariable String id) {
        usersInRooms.put(id, usersInRooms.get(id) + 1);
        this.template.convertAndSend("/topic/room/"+id, usersInRooms.get(id));
    }
}
