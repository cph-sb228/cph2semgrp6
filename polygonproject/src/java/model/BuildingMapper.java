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
    

    public static boolean insertBuilding(Building building) throws ClassNotFoundException {

        try {
            String sql = "INSERT INTO `buildings` (`owner`,`address`) VALUES (?,?);";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setString(1, building.getOwner());
            ps.setString(2, building.getAddress());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Exception Caught in instertBuilding" + ex.getMessage());
            return false;
        }
        return true;
    }

    public static List<Building> getBuildings() throws ClassNotFoundException {

        List<Building> buildings = new ArrayList();

        try {

            String sql = "SELECT * FROM `buildings`;";
            DBAccess DB = DBAccess.getInstance();
            Statement st = DB.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String owner = rs.getString("owner");
                String address = rs.getString("address");
                Building building = new Building(owner, address);
                buildings.add(building);
            }

            return buildings;
        } catch (SQLException ex) {
            System.out.println("DU BIST EIN TABER!!" + ex.getMessage());
            return null;
        }

    }
}
