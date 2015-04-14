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
public class Users {

    private String userLogin;
    private String userPassword;
    private String userName;
    private Date userRegistrationdate;
    private int userIsadmin;

    public Users() {
    }

    public Users(String userLogin, String userPassword) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public Users(String userLogin, int userIsadmin) {
        this.userLogin = userLogin;
        this.userIsadmin = userIsadmin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserRegistrationdate() {
        return userRegistrationdate;
    }

    public void setUserRegistrationdate(Date userRegistrationdate) {
        this.userRegistrationdate = userRegistrationdate;
    }

    public int getUserIsadmin() {
        return userIsadmin;
    }

    public void setUserIsadmin(int userIsadmin) {
        this.userIsadmin = userIsadmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userLogin != null ? userLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userLogin == null && other.userLogin != null) || (this.userLogin != null && !this.userLogin.equals(other.userLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.kpi.nc.entities.Users[ userLogin=" + userLogin + " ]";
    }

}
