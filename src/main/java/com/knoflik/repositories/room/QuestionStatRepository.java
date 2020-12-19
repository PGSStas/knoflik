package com.knoflik.repositories.room;

import com.knoflik.entities.QuestionStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionStatRepository
        extends JpaRepository<QuestionStat, String> {
}
