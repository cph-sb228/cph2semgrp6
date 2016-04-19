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
    public static boolean insertBuilding(Building building) throws PolygonException {
        String sql = "INSERT INTO `buildings` (`owner`,`address`,`housenr`,`zipcode`,`city`,`floor`,`km2`,`conditions`) VALUES (?,?,?,?,?,?,?,?);";

        try (PreparedStatement ps = DBAccess.prepare(sql)) {

            ps.setString(1, building.getOwner());
            ps.setString(2, building.getAddress());
            ps.setInt(3, building.getHousenr());
            ps.setInt(4, building.getZipcode());
            ps.setString(5, building.getCity());
            ps.setInt(6, building.getFloor());
            ps.setInt(7, building.getKm2());
            ps.setString(8, building.getConditions());
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "insert building fejlede";
            throw new PolygonException(msg);
        }
        return true;
    }

    //SQL select request, which returns a list of buildings depending on user type
    public static List<Building> getBuildings(String ownerName, String ownerType) throws PolygonException {

        List<Building> buildings = new ArrayList();
            String sql = "";

        try {

            if (ownerType.equals("polygon")) {
                sql = "SELECT * FROM `buildings`;";
            } else {
                sql = "SELECT * FROM `buildings` WHERE `owner` = '" + ownerName + "';";
            }

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

            st.close();
            return buildings;
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "get buildings til liste fejlede";
            throw new PolygonException(msg);
        }
    }

    //SQL request which deletes a building from the database, by using its unique ID
    public static boolean removeBuilding(int id) throws PolygonException {
        String sql = "DELETE FROM buildings WHERE id = ?;";

        try (PreparedStatement ps = DBAccess.prepare(sql)){
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "kunne ikke slette bygning";
            throw new PolygonException(msg);
        }

        return true;
    }
}
