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
public class Questions {

    private int questionId;
    private int topicId;
    private int messageId;
    private String userLogin;
    private String messageText;
    private Date messageDate;

    public Questions() {
    }

    public Questions(int questionId) {
        this.questionId = questionId;
    }

    public Questions(int topicId, String userLogin, String messageText) {
        this.topicId = topicId;
        this.userLogin = userLogin;
        this.messageText = messageText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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
        return "ua.kpi.nc.entities.Questions[ questionId=" + questionId + " ]";
    }

}
