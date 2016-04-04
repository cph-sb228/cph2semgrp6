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
    

    public boolean insertBuilding(Building building) throws ClassNotFoundException {

        try {
            String sql = "INSERT INTO 'building' ('owner','address') VALUES ('?','?');";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setString(1, building.getOwner());
            ps.setString(2, building.getAddress());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Exception Caught in instertBuilding");
            return false;
        }
        return true;
    }

    public static List<Building> getBuildings() throws ClassNotFoundException {
            System.out.println("-1");

        List<Building> buildings = new ArrayList();
                    System.out.println("0");

        try {

            String sql = "SELECT * FROM `buildings`;";
            System.out.println("0.5");
            DBAccess DB = DBAccess.getInstance();
            System.out.println("1");
            Statement st = DB.getCon().createStatement();
            System.out.println("2");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String owner = rs.getString("owner");
            System.out.println("3");
                String address = rs.getString("address");
            System.out.println("4");
                Building building = new Building(owner, address);
            System.out.println("5");
                buildings.add(building);
            System.out.println("6");
            }
            System.out.println("7");

            return buildings;
        } catch (SQLException ex) {
            System.out.println("DU BIST EIN TABER!!" + ex.getMessage());
            return null;
        }

    }
}
