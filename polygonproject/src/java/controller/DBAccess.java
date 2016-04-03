/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author terfy
 */
public class DBAccess {
    private Connection con;
    private static DBAccess instance;
    private static PreparedStatement stmt;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/polygon";
    private static String id = "polyadmin";			
    private static String pw = "1234";
    
     public static DBAccess getInstance() {
        if (instance == null) {
            instance = new DBAccess();
        }
        return instance;
    }

    public Connection getCon() {
        return this.con;
    }
     

    public static PreparedStatement prepare(String SQLString) {
        try {
            stmt = getInstance().con.prepareStatement(SQLString);
        } catch (SQLException e) {
            System.out.println("Error in DB.prepare()" + e);
        }
        return stmt;
    }
    
}