/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Report;
import controller.DBAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author terfy
 */
public class ReportMapper {

    public static boolean insertReport(Report report) throws ClassNotFoundException {

        try {
            String sql = "INSERT INTO `reports` (`filedir`,`buildingID`,`buildingcondition`) VALUES (?,?,?);";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setString(1, report.getFiledir());
            ps.setInt(2, report.getBuildingID());
            ps.setString(3, report.getBuildingcondition());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Exception Caught in instertBuilding" + ex.getMessage());
            return false;
        }
        return true;
    }

    public static List<Report> getBuildings() throws ClassNotFoundException {

        List<Report> reports = new ArrayList();

        try {

            String sql = "SELECT * FROM `reports`;";
            DBAccess DB = DBAccess.getInstance();
            Statement st = DB.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String filedir = rs.getString("filedir");
                int buildingID = rs.getInt("buildingID");
                String buildingcondition = rs.getString("buildingcondition");
                Report report = new Report(filedir, buildingID, buildingcondition);
                report.setId(rs.getInt("id"));
                reports.add(report);
            }

            return reports;
        } catch (SQLException ex) {
            System.out.println("DU BIST EIN REPORT TABER!!" + ex.getMessage());
            return null;
        }
    }

    public static boolean removeReport(int id) throws ClassNotFoundException {

        try {

            String sql = "DELETE FROM reports WHERE id = ?;";
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
