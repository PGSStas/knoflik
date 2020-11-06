package com.knoflik.restknoflik.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Random;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final HashSet<String> set = new HashSet<>();
    private final Random rnd = new Random();

    @GetMapping
    public HashSet<String> getRooms() {
        return set;
    }

    @GetMapping("/new")
    public String createRoom() {
        int a;
        do {
            a = rnd.nextInt(1000000);
        } while (set.contains(Integer.toString(a)));
        String s = Integer.toString(a);
        set.add(s);
        return s;
    }

    @GetMapping("/{id}")
    public String getRoom(@PathVariable String id) {
        if (set.contains(id)) {
            return "200";
        } else {
            return "404";
        }
    }
}
