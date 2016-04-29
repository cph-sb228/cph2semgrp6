    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import dataaccess.DBAccess;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import domain.PolygonException;
import domain.User;

/**
 *
 * @author terfy
 */
public class UsersMapper {
    
    //Creates a user from user input
    public static void insertUser(User user) throws PolygonException {
        String sql = "INSERT INTO `users` (`username`,`password`, `email`, `type`) VALUES (?,?,?,?);";
        
        try (PreparedStatement ps = DBAccess.prepare(sql)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getType());
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "user could not be created";
            throw new PolygonException(msg);
        }
    }

    //Returns a list of all users in the system
    public static List<User> getUser() throws PolygonException{

        List<User> users = new ArrayList();
        String sql = "SELECT * FROM `users`;";

        try {
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

            st.close();
            return users;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("TIS getUser fails");
            String msg = "list of users could not be found";
            throw new PolygonException(msg);
        }
    }
    
    //Remove a specific user based on its ID
    public static boolean removeUser(int id) throws PolygonException {
            String sql = "DELETE FROM users WHERE id = ?;";

        try (PreparedStatement ps = DBAccess.prepare(sql)){
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "user could not be removed";
            throw new PolygonException(msg);
        }

        return true;
    }
}
