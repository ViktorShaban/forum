/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.IDAO;

import java.util.List;
import ua.kpi.nc.entities.Users;

/**
 *
 * @author Виктор
 */
public interface UsersIDAO {

    public boolean checkLogin(Users users, int i);

    public Users findUserInfoByLogin(Users users);
    
    public void addUser(Users users);
    
    public void deleteUser(String userLogin);
    
    public List<Users> findAllUsers();
    
    public void updateUserStatus(Users users);
}
