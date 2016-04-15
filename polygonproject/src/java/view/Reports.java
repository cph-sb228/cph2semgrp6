/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Building;
import controller.Report;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.BuildingMapper;
import model.ReportMapper;

/**
 *
 * @author terfy
 */
@MultipartConfig
public class Reports extends HttpServlet {

    private void removeReport(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            ReportMapper.removeReport(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean addReport(HttpServletRequest req) throws IOException, ServletException {
        String buildingID = req.getParameter("buildingID");
        String itemname = req.getParameter("itemname");
        String itemproblem = (String) req.getParameter("itemproblem");
        String floor = (String) req.getParameter("floor");
        String roomnumber = (String) req.getParameter("roomnumber");
        String importancy = (String) req.getParameter("importancy");
        String comments = (String) req.getParameter("comments");
        List<Part> fileParts = Collections.emptyList();
        
        if(req.getPart("file").getSize() > 0){
            fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
        }
        
        if (itemname.length() > 0
                && itemproblem.length() > 0
                && floor.length() > 0
                && importancy.length() > 0
                ) {
            Report report = new Report(itemname, itemproblem, floor, roomnumber, importancy, comments);
            report.setBuildingID(Integer.parseInt(buildingID));
            try {
                ReportMapper.insertReport(report);
                System.out.println("insert report");
                ReportMapper.insertBlob(report, fileParts);
                return true;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private void prepareReportList(HttpServletRequest req) {
        List<Report> reports = null;
        try {
            reports = ReportMapper.getReports();
        } catch (ClassNotFoundException ex) {
            System.out.println("byumssdflksdlkfsdkl sdfkllksdf fucking.. hell NO");
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.getSession().setAttribute("reports", reports);
    }
    
    private void prepareBuildingList(HttpServletRequest req) {
        List<Building> buildings = null;
        String ownerName = (String) req.getSession().getAttribute("logged_in_name");
        String ownerType = (String) req.getSession().getAttribute("logged_in_type");
        try {
            buildings = BuildingMapper.getBuildings(ownerName, ownerType);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.getSession().setAttribute("buildings", buildings);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String do_this = req.getParameter("do_this");

        switch (do_this) {
            case "delete":
                removeReport(req);
                prepareReportList(req);
                resp.sendRedirect("reportlist.jsp");
                break;

            case "add":
                System.out.println("add knappen virker");
                if (addReport(req)) {
                    prepareReportList(req);
                    resp.sendRedirect("reportlist.jsp");
                } else {
                    resp.sendRedirect("reportadd.jsp");
                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareBuildingList(req);
        prepareReportList(req);
        resp.sendRedirect("reportlist.jsp");
    }

}
