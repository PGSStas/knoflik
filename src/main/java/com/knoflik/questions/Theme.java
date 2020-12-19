package com.knoflik.questions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String themeName;
    @OneToMany
    @JoinColumn(name = "questions")
    private List<Question> questionSet = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(final String themeName) {
        this.themeName = themeName;
    }

    public List<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(final List<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public void addQuestion(final Question question) {
        questionSet.add(question);
    }
}
