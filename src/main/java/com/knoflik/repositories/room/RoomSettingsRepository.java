package com.knoflik.repositories.room;

import com.knoflik.entities.RoomSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomSettingsRepository
        extends JpaRepository<RoomSettings, String> {
}
