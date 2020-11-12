package com.knoflik.services;

import com.knoflik.entities.Room;
import com.knoflik.entities.User;
import com.knoflik.repositories.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserService userService;

    public Room createRoom() {
        Room room = new Room();
        User user = userService.createUser();
        room.setAdmin(user);
        roomRepository.save(room);
        return room;
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id).orElse(null);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public boolean addUserToRoom(String room_id) {
        Room room = roomRepository.findById(room_id).orElse(null);
        if (room == null) {
            return false;
        }

        User user = userService.createUser();
        room.addUser(user);
        roomRepository.save(room);
        return true;
    }

    public Set<User> getAllUsersFromRoom(String room_id) {
        Room room = roomRepository.findById(room_id).orElse(null);
        if (room == null) {
            return null;
        }
        return room.getActiveUsers();
    }
}
