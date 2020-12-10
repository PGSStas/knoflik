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
        User user = userService.getLoggedUser();
        // room.setAdmin(user);
        roomRepository.save(room);
        return room;
    }

    public Room getRoomById(final String id) {
        return roomRepository.findById(id).orElse(null);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public boolean addUserToRoom(final String roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);

        if (room == null) {
            return false;
        }

        User user = userService.getLoggedUser();
        if (room.getActiveUsers().stream().anyMatch(u -> user.getUsername()
                .equals(u.getUsername()))) {
            return true;
        }
        user.setCurrentRoom(room);
        userService.saveUser(user);
        return true;
    }

    public Set<User> getAllUsersFromRoom(final String roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            return null;
        }
        return room.getActiveUsers();
    }

    public void removeUserFromRoom(final String roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            return;
        }

        User user = userService.getLoggedUser();
        user.setCurrentRoom(null);
        userService.saveUser(user);
    }
}
