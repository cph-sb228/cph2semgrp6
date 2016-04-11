/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.DBAccess;
import controller.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author terfy
 */
public class UsersMapper {
    
    public static boolean insertUser(User user) throws ClassNotFoundException {

        try {
            String sql = "INSERT INTO `users` (`username`,`password`, `email`, `type`) VALUES (?,?,?,?);";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getType());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Exception Caught in insertUser" + ex.getMessage());
            return false;
        }
        return true;
    }

    public static List<User> getUser() throws ClassNotFoundException {

        List<User> users = new ArrayList();

        try {

            String sql = "SELECT * FROM `users`;";
            DBAccess DB = DBAccess.getInstance();
            Statement st = DB.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String type = rs.getString("type");
                User user = new User(username, password, email, type);
                user.setId(rs.getInt("id"));
                users.add(user);
            }

            return users;
        } catch (SQLException ex) {
            System.out.println("DU BIST EIN USER TABER!!" + ex.getMessage());
            return null;
        }
    }

    public static boolean removeUser(int id) throws ClassNotFoundException {

        try {

            String sql = "DELETE FROM users WHERE id = ?;";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setInt(1,id);
            
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Exception in removeUsers");
            return false;
        }

        return true;
    }
}
