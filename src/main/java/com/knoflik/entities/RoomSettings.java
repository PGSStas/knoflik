package com.knoflik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "rooms_settings")
public class RoomSettings {
    @Transient
    private final int columnLength = 32;

    @Id
    private String id;

    @Column(name = "pack_id", length = columnLength)
    private String packID = null;

    @Column(name = "topics", length = columnLength)
    private int topics;

    @Column(name = "question_time", length = columnLength)
    private int questionTime;

    @Column(name = "answer_time", length = columnLength)
    private int answerTime;

    @Column(name = "repeat_time", length = columnLength)
    private int repeatTime;

    @Column(name = "leader", length = columnLength)
    private boolean leader;

    @Column(name = "show_questions", length = columnLength)
    private boolean showQuestions;

    @Column(name = "oral_answer", length = columnLength)
    private boolean oralAnswer;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(final String packID) {
        this.packID = packID;
    }

    public int getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(final int questionTime) {
        this.questionTime = questionTime;
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(final int answerTime) {
        this.answerTime = answerTime;
    }

    public int getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(final int repeatTime) {
        this.repeatTime = repeatTime;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(final boolean leader) {
        this.leader = leader;
    }

    public boolean isShowQuestions() {
        return showQuestions;
    }

    public void setShowQuestions(final boolean showQuestions) {
        this.showQuestions = showQuestions;
    }

    public boolean isOralAnswer() {
        return oralAnswer;
    }

    public void setOralAnswer(final boolean oralAnswer) {
        this.oralAnswer = oralAnswer;
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(final int topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "RoomSettings{" + "id='" + id + '\'' + ", packID='" + packID
                + '\'' + ", topics=" + topics + ", questionTime=" + questionTime
                + ", answerTime=" + answerTime + ", repeatTime=" + repeatTime
                + ", leader=" + leader + ", showQuestions=" + showQuestions
                + ", oralAnswer=" + oralAnswer + '}';
    }
}
