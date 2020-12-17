package com.knoflik.entities;

import com.knoflik.questions.Pack;
import com.knoflik.questions.Question;
import com.knoflik.questions.Theme;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "question_stat")
public class QuestionStat {
    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    private Pack currentPack;
    private int currentTheme = 0;
    private int currentQuestion = -1;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> answeredUsers;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Pack getCurrentPack() {
        return currentPack;
    }

    public void setCurrentPack(final Pack currentPack) {
        this.currentPack = currentPack;
    }

    public int getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(final int currentTheme) {
        this.currentTheme = currentTheme;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(final int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Set<User> getAnsweredUsers() {
        return answeredUsers;
    }

    public void setAnsweredUsers(final Set<User> answeredUsers) {
        this.answeredUsers = answeredUsers;
    }

    public Question getNextQuestion() {
        currentQuestion++;
        if (currentQuestion >= 5) {
            currentTheme++;
            currentQuestion = 0;
        }
        if (currentTheme >= currentPack.getThemes().size()) {
            return new Question();
        }
        Theme theme = currentPack.getThemes().get(currentTheme);
        return theme.getQuestionSet().get(currentQuestion);
    }
}
