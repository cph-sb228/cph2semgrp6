package dataaccess;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;
import domain.PolygonException;
import domain.Report;

/**
 *
 * @author terfy
 */
public class ReportMapper {
    
    public static boolean insertBlob(Report report, List<Part> fileparts) throws PolygonException {
        if(fileparts.isEmpty()) return true;
        for (Part file : fileparts) {
            String sql = "INSERT INTO `files` ("
                    + "`reportId`,"
                    + "`filename`,"
                    + "`file`"
                    + ") VALUES (?,?,?);";
            try (PreparedStatement ps = DBAccess.prepare(sql)){
                System.out.println(report.getId());
                ps.setInt(1, report.getId());
                String filename = file.getHeader("Content-Disposition");
                filename = filename.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1");
                ps.setString(2, filename);
                ps.setBlob(3, file.getInputStream());
                ps.execute();
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                String msg = "kunne ikke insert fil i database";
                throw new PolygonException(msg + " " + ex.getMessage());
            }
        }
        return true;
    }

    // creates a SQL statement from user input, which creates a report.
    public static boolean insertReport(Report report) throws PolygonException {
        String sql = "INSERT INTO `reports` ("
                + "`buildingID`,"
                + "`itemname`,"
                + "`itemproblem`,"
                + "`floor`,"
                + "`roomnumber`,"
                + "`importancy`,"
                + "`comments`"
                + ") VALUES (?,?,?,?,?,?,?);";

        try (PreparedStatement ps = DBAccess.prepare(sql)){
            ps.setInt(1, report.getBuildingID());
            ps.setString(2, report.getItemname());
            ps.setString(3, report.getItemproblem());
            ps.setString(4, report.getFloor());
            ps.setString(5, report.getRoomnumber());
            ps.setString(6, report.getImportancy());
            ps.setString(7, report.getComments());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) report.setId(rs.getInt(1));
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "kunne ikke oprette report";
            throw new PolygonException(msg);
        }
        return true;
    }

    private static List<String> getFilenameList(int s) throws PolygonException{
        
        List<String> filename = new ArrayList<>();
        String sql = "SELECT * FROM `files` WHERE `reportId` = " + s + ";";

        try {
            DBAccess DB = DBAccess.getInstance();
            Statement st = DB.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            String name = "";
            while (rs.next()) {
                name = rs.getString("filename");
                filename.add(name);
            }
            
            st.close();
            return filename;
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "kunne ikke hente filename fra files";
            throw new PolygonException(msg);
        }
    }
    
    // returns a full list of all the reports.
    public static List<Report> getReports() throws PolygonException {

        List<Report> reports = new ArrayList();
        List<String> filenames = new ArrayList();
        
        String sql = "SELECT * FROM `reports`;";

        try {
            DBAccess DB = DBAccess.getInstance();
            Statement st = DB.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                filenames = getFilenameList(rs.getInt("id"));
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
                report.setFilenames(filenames);
                reports.add(report);
            }
            
            st.close();
            return reports;
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "kunne ikke hente reports til liste";
            throw new PolygonException(msg);
        }
    }
    //SQL statement that removes a report based on its ID
    public static boolean removeReport(int id) throws PolygonException {
        String sql = "DELETE FROM reports WHERE id = ?;";

        try (PreparedStatement ps = DBAccess.prepare(sql)){
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "kunne ikke slette report";
            throw new PolygonException(msg);
        }

        return true;
    }
}
