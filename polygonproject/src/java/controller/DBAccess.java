/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;



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
    
    private DBAccess() throws SQLException, ClassNotFoundException{
        Class.forName(driver);
        this.con = (Connection) DriverManager.getConnection(URL,id,pw);
    }
    
    public static DBAccess getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DBAccess();
            
        }
        return instance;
    }

    public Connection getCon() {
        return this.con;
    }
     

    public static PreparedStatement prepare(String SQLString) throws ClassNotFoundException {
        try {
            stmt = (PreparedStatement) getInstance().getCon().prepareStatement(SQLString);
        } catch (SQLException e) {
            System.out.println("Error in DB.prepare()" + e.getMessage());
        }
        return stmt;
    }
    
}