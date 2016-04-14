/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Report;
import controller.DBAccess;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

/**
 *
 * @author terfy
 */
public class ReportMapper {
    
    
    public static boolean insertBlob(Report report, List<Part> fileparts) throws ClassNotFoundException, IOException {
        for (Part file : fileparts) {
            System.out.println("vi har filerne med");
            try {
                String sql = "INSERT INTO `files` ("
                        + "`reportId`,"
                        + "`filename`,"
                        + "`file`"
                        + ") VALUES (?,?,?);";
                PreparedStatement ps = DBAccess.prepare(sql);
                ResultSet rs = ps.getGeneratedKeys();
                int i = 0;
                if (rs.next()){
                    
                    System.out.println(i + " getInt ");
                } else System.out.println("har ingen next");
                report.setId(i);
                System.out.println(report.getId() + " report id ");
                
                ps.setInt(1, report.getId());
                ps.setString(2, file.getName());
                ps.setBlob(3, file.getInputStream());
                ps.execute();
            } catch (SQLException ex) {
                System.out.println("Exception Caught in instertReport" + ex.getMessage());
                return false;
            }
        }
        return true;
    }

    public static boolean insertReport(Report report) throws ClassNotFoundException {

        try {
            String sql = "INSERT INTO `reports` ("
                    + "`buildingID`,"
                    + "`itemname`,"
                    + "`itemproblem`,"
                    + "`floor`,"
                    + "`roomnumber`,"
                    + "`importancy`,"
                    + "`comments`"
                    + ") VALUES (?,?,?,?,?,?,?);";
            PreparedStatement ps = DBAccess.prepare(sql);
            ps.setInt(1, report.getBuildingID());
            ps.setString(2, report.getItemname());
            ps.setString(3, report.getItemproblem());
            ps.setString(4, report.getFloor());
            ps.setString(5, report.getRoomnumber());
            ps.setString(6, report.getImportancy());
            ps.setString(7, report.getComments());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Exception Caught in instertReport " + ex.getMessage());
            return false;
        }
        return true;
    }

    public static List<Report> getReports() throws ClassNotFoundException {

        List<Report> reports = new ArrayList();

        try {

            String sql = "SELECT * FROM `reports`;";
            DBAccess DB = DBAccess.getInstance();
            Statement st = DB.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int buildingID = rs.getInt("buildingID");
                String itemname = rs.getString("itemname");
                String itemproblem = rs.getString("itemproblem");
                String floor = rs.getString("floor");
                String roomnumber = rs.getString("roomnumber");
                String importancy = rs.getString("importancy");
                String comments = rs.getString("comments");
                Report report = new Report(itemname, itemproblem, floor, roomnumber, importancy, comments);
                report.setBuildingID(buildingID);
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
