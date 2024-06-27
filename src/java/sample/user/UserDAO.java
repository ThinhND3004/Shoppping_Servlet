/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author ACER
 */
public class UserDAO {

    private static final String LOGIN = "SELECT fullName, roleID FROM tblUsers WHERE userID=? AND password=? ";
//    private static final String SEARCH="SELECT userID, fullName, roleID FROM tblUsers WHERE fullNae like ? ";
    private static final String SEARCH = "SELECT userID, fullName, roleID, password FROM tblUsers where fullName LIKE ? AND status = 'True'";
    private static final String DELETE = "UPDATE tblUsers SET status = 'False' WHERE tblUsers.userID = ?";
    private static final String UPDATE = "UPDATE tblUsers SET fullName = ? , roleID = ? WHERE userID = ?";

    private static final String DELETE_LOGIN_USER_ERROR = "Cannot delete this user!";
    private static final String DUP = "SELECT fullName FROM tblUsers WHERE userID=? ";
    private static final String INSERT = "INSERT INTO tblUsers(userID, fullName, roleID, password, status) VALUES (?,?,?,?, 'True')";

    public UserDTO checkLogin(String userID, String password) throws SQLException, ClassNotFoundException {
        UserDTO user = null;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {

                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");

                    user = new UserDTO(userID, fullName, roleID, "");
                }
            }
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException, ClassNotFoundException {
        List<UserDTO> resultList = new ArrayList<UserDTO>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO user = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = rs.getString("password");

                    user = new UserDTO(userID, fullName, roleID, password);
                    resultList.add(user);
                }
            }
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return resultList;
    }

    public String delete(String userID, UserDTO loginUser) throws SQLException, ClassNotFoundException {
        String result = "";

        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                if (loginUser.getUserID().equals(userID)) {
                    result = DELETE_LOGIN_USER_ERROR;
                } else {
                    ptm = conn.prepareStatement(DELETE);
                    ptm.setString(1, userID);
                    result = ptm.executeUpdate() > 0 ? "": DELETE_LOGIN_USER_ERROR;
                }

            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean update(UserDTO updateUser) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, updateUser.getFullName());
                ptm.setString(2, updateUser.getRoleID());
                ptm.setString(3, updateUser.getUserID());
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = false;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        
        
        return check;
    }

    public boolean checkDuplicate(String userID) throws SQLException, ClassNotFoundException {
        boolean check = false;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUP);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {

                    check = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insert(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
       

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                check = ptm.executeUpdate()> 0 ? true : false;
            }
        }finally {
           
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        
        
        return check;
    }

}
