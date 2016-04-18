/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Building;
import controller.DBAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author terfy
 */
public class BuildingMapper {

    
    //SQL request to add a building
    public static boolean insertBuilding(Building building) throws ClassNotFoundException {

        try {
            String sql = "INSERT INTO `buildings` (`owner`,`address`,`housenr`,`zipcode`,`city`,`floor`,`km2`,`conditions`) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setString(1, building.getOwner());
            ps.setString(2, building.getAddress());
            ps.setInt(3, building.getHousenr());
            ps.setInt(4, building.getZipcode());
            ps.setString(5, building.getCity());
            ps.setInt(6, building.getFloor());
            ps.setInt(7, building.getKm2());
            ps.setString(8, building.getConditions());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Exception Caught in instertBuilding" + ex.getMessage());
            return false;
        }
        return true;
    }
    
    //SQL select request, which returns a list of buildings depending on user type
    public static List<Building> getBuildings(String ownerName, String ownerType) throws ClassNotFoundException {

        List<Building> buildings = new ArrayList();
        
        try {
            String sql = "";

            if (ownerType.equals("polygon")){
                sql = "SELECT * FROM `buildings`;";
            } else sql = "SELECT * FROM `buildings` WHERE `owner` = '"+ownerName+"';";
            
            DBAccess DB = DBAccess.getInstance();
            Statement st = DB.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String owner = rs.getString("owner");
                String address = rs.getString("address");
                int housenr = rs.getInt("housenr");
                int zipcode = rs.getInt("zipcode");
                String city = rs.getString("city");
                int floor = rs.getInt("floor");
                int km2 = rs.getInt("km2");
                String conditions = rs.getString("conditions");
                Building building = new Building(owner, address, housenr, zipcode, city, floor, km2, conditions);
                building.setId(rs.getInt("id"));
                buildings.add(building);
            }

            return buildings;
        } catch (SQLException ex) {
            System.out.println("DU BIST EIN TABER!!" + ex.getMessage());
            return null;
        }
    }

    //SQL request which deletes a building from the database, by using its unique ID
    public static boolean removeBuilding(int id) throws ClassNotFoundException {

        try {

            String sql = "DELETE FROM buildings WHERE id = ?;";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setInt(1,id);
            
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Exception in removeBuilding");
            return false;
        }

        return true;
    }
}
