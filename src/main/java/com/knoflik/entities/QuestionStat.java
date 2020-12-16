package com.knoflik.entities;

import com.knoflik.questions.Theme;
import com.knoflik.rest.QnAController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.TimerTask;

public class QuestionStat {
    @Autowired
    private QnAController qnaController;

    private RoomSettings roomSettings;

    private TimerTask timerTask;
    private Set<Theme> themeSet;

    QuestionStat(final RoomSettings roomSettings) {
        this.roomSettings = roomSettings;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public void setTimerTask(final TimerTask timerTask) {
        this.timerTask = timerTask;
    }

    public Set<Theme> getThemeSet() {
        return themeSet;
    }

    public void setThemeSet(final Set<Theme> themeSet) {
        this.themeSet = themeSet;
    }

    public RoomSettings getRoomSettings() {
        return roomSettings;
    }

    public void setRoomSettings(final RoomSettings roomSettings) {
        this.roomSettings = roomSettings;
    }

    public void deleteTimer() {
        timerTask.cancel();
    }

    public void nextQuestion() {
        qnaController.
    }
}
