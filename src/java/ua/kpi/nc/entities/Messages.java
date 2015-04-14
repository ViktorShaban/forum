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
public class Messages {

    private int recordId;
    private int messageId;
    private String userLogin;
    private String messageText;
    private Date messageDate;

    public Messages() {
    }

    public Messages(int messageId, String userLogin, String messageText, Date messageDate) {
        this.messageId = messageId;
        this.userLogin = userLogin;
        this.messageText = messageText;
        this.messageDate = messageDate;
    }

    public Messages(int messageId) {
        this.messageId = messageId;
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

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Override
    public String toString() {
        return "ua.kpi.nc.entities.Messages[ messageId=" + messageId + " ]";
    }

}
