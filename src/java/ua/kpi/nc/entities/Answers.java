/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.entities;

import java.util.Date;

/**
 *
 * @author Виктор
 */
public class Answers {

    private int answerId;
    private int questionId;
    private int messageId;
    private String userLogin;
    private String messageText;
    private Date messageDate;

    public Answers(int questionId, String userLogin, String messageText) {
        this.questionId = questionId;
        this.userLogin = userLogin;
        this.messageText = messageText;
    }

    public Answers() {
    }

    public Answers(int answerId) {
        this.answerId = answerId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "ua.kpi.nc.entities.Answers[ answerId=" + answerId + " ]";
    }

}
