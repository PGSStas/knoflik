package com.knoflik.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoflik.entities.Room;
import com.knoflik.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/rooms")
public final class RoomController {
    /**
     * RoomService that prov
     */
    @Autowired
    private RoomService roomService;

    private final SimpMessagingTemplate template;

    @Autowired
    public RoomController(final SimpMessagingTemplate newTemplate) {
        this.template = newTemplate;
    }

    @GetMapping
    public String getRooms() {
        return roomService.getAllRooms().toString();
    }

    @GetMapping("/new")
    public String createRoom() {
        return roomService.createRoom().getId();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable final String id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/{id}/addUser")
    public void addUser(@PathVariable final String id) throws Exception {
        if (roomService.addUserToRoom(id)) {
            this.template.convertAndSend("/topic/room/" + id,
                    (new ObjectMapper()).writeValueAsString(
                            roomService.getAllUsersFromRoom(id)));
        }
    }
}
