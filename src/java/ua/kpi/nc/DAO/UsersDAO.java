/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.kpi.nc.IDAO.UsersIDAO;
import ua.kpi.nc.connection.ConnectionFactory;
import ua.kpi.nc.entities.Users;

/**
 *
 * @author Виктор
 */
public class UsersDAO implements UsersIDAO {

    private Connection connection;

    @Override
    public boolean checkLogin(Users users, int i) {
        PreparedStatement ptmt = null;
        try {
            switch (i) {
                case 1:
                    String sql = "select * from FORUMADMIN.USERS "
                            + "WHERE USER_LOGIN = ? "
                            + "AND USER_PASSWORD = ?";
                    connection = ConnectionFactory.getConnection();
                    ptmt = connection.prepareStatement(sql);
                    ptmt.setString(1, users.getUserLogin());
                    ptmt.setString(2, users.getUserPassword());
                    ResultSet rs = ptmt.executeQuery();
                    return rs.next();

                case 2:
                    String sql1 = "select * from FORUMADMIN.USERS "
                            + "WHERE USER_LOGIN = ?";
                    connection = ConnectionFactory.getConnection();
                    ptmt = connection.prepareStatement(sql1);
                    ptmt.setString(1, users.getUserLogin());            
                    ResultSet rs1 = ptmt.executeQuery();
                    return rs1.next();

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersIDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    ConnectionFactory.freeConnection(connection);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsersIDAO.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Users findUserInfoByLogin(Users users) {
        String sql = "select * from FORUMADMIN.USERS WHERE FORUMADMIN.USERS.USER_LOGIN = ?";
        PreparedStatement ptmt = null;
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, users.getUserLogin());
            ResultSet rs = ptmt.executeQuery();
            rs.next();
            users.setUserPassword(rs.getString("USER_PASSWORD"));
            users.setUserName(rs.getString("USER_NAME"));
            users.setUserRegistrationdate(rs.getTimestamp("USER_REGISTRATIONDATE"));
            users.setUserIsadmin(rs.getInt("USER_ISADMIN"));
        } catch (SQLException ex) {
            Logger.getLogger(UsersIDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    ConnectionFactory.freeConnection(connection);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsersIDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    @Override
    public void addUser(Users users) {
        String sql = "INSERT INTO FORUMADMIN.USERS (USER_LOGIN, USER_PASSWORD, USER_NAME,"
                + " USER_REGISTRATIONDATE, USER_ISADMIN) VALUES (?,?,?,?,0)";
        PreparedStatement ptmt = null;
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, users.getUserLogin());
            ptmt.setString(2, users.getUserPassword());
            ptmt.setString(3, users.getUserName());
            ptmt.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersIDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    ConnectionFactory.freeConnection(connection);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsersIDAO.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void deleteUser(String userLogin) {
        PreparedStatement ptmt = null;
        String queryString = "DELETE FROM FORUMADMIN.USERS WHERE USER_LOGIN=?";
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, userLogin);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    ConnectionFactory.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Users> findAllUsers() {
        String sql = "SELECT * FROM FORUMADMIN.Users";
        PreparedStatement ptmt = null;
        List<Users> list = new ArrayList<Users>();
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Users users = new Users();
                users.setUserLogin(rs.getString("USER_LOGIN"));
                users.setUserPassword(rs.getString("USER_PASSWORD"));
                users.setUserName(rs.getString("USER_NAME"));
                users.setUserRegistrationdate(rs.getTimestamp("USER_REGISTRATIONDATE"));
                users.setUserIsadmin(rs.getInt("USER_ISADMIN"));
                list.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    ConnectionFactory.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void updateUserStatus(Users users) {
        String sql = "UPDATE FORUMADMIN.Users SET USER_ISADMIN = ? WHERE USER_LOGIN = ?";
        PreparedStatement ptmt = null;
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setInt(1, users.getUserIsadmin());
            ptmt.setString(2, users.getUserLogin());
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    ConnectionFactory.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

}
