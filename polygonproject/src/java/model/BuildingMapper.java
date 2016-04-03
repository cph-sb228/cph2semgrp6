/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Building;
import controller.DBAccess;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author terfy
 */
public class BuildingMapper {
    void addbuilding(){  
    }
    
    public boolean insertBuilding(Building building)    {
        
        try {
            String sql = "INSERT INTO 'building' ('owner','address') VALUES ('?','?');";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setString(1,building.getOwner());
            ps.setString(2,building.getAddress());
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Exception Caught in instertBuilding");
            return false;
        }
        return true;
    }
}