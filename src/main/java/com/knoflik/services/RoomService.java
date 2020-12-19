package com.knoflik.services;

import com.knoflik.entities.Room;
import com.knoflik.entities.RoomSettings;
import com.knoflik.entities.User;
import com.knoflik.repositories.room.RoomRepository;
import com.knoflik.repositories.room.RoomSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomSettingsRepository roomSettingsRepository;
    @Autowired
    private UserService userService;

    private String generateId() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            id.append((char) (65 + random.nextInt(26)));
        }
        return id.toString();
    }

    public void saveRoom(final Room room) {
        roomRepository.save(room);
    }

    public String createRoom(final RoomSettings settings) {
        String id;
        do {
            id = generateId();
        } while (roomRepository.existsById(id));
        settings.setId(id);
        roomSettingsRepository.save(settings);

        Room room = new Room();
        room.setId(id);
        room.setSettings(settings);
        User user = userService.getLoggedUser();
        room.setAdmin(user);

        roomRepository.save(room);
        user.setCurrentRoom(room);
        user.setAdmin(true);
        userService.saveUser(user);

        return id;
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
        room.addUser(user);
        roomRepository.save(room);
        return true;
    }

    public Set<User> getAllUsersFromRoom(final String roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            return null;
        }
        return room.getActiveUsers();
    }
}
