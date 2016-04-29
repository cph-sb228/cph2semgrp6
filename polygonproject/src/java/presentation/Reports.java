/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.Building;
import domain.Report;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import dataaccess.BuildingMapper;
import domain.PolygonException;
import dataaccess.ReportMapper;

/**
 *
 * @author terfy
 */
@MultipartConfig
public class Reports extends HttpServlet {
    
    RequestDispatcher rd = null;

    //method is called upon delete-button press and sends an ID to the report mapper. 
    private void removeReport(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            ReportMapper.removeReport(id);
        } catch (PolygonException ex) {
            req.getSession().setAttribute("errorMsg", ex.getMessage());
        }
    }

    private void addReport(HttpServletRequest req) throws PolygonException {
        String buildingID = req.getParameter("buildingID");
        String itemname = req.getParameter("itemname");
        String itemproblem = (String) req.getParameter("itemproblem");
        String floor = (String) req.getParameter("floor");
        String roomnumber = (String) req.getParameter("roomnumber");
        String importancy = (String) req.getParameter("importancy");
        String comments = (String) req.getParameter("comments");
        List<Part> fileParts = Collections.emptyList();

        try {
            if (req.getPart("file").getSize() > 0) {
                fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
            }
        } catch (IOException | ServletException ex) {
            String msg = "fejl i filepart";
            throw new PolygonException(msg);
        }

        if (itemname.length() > 0
                && itemproblem.length() > 0
                && floor.length() > 0
                && importancy.length() > 0) {
            
            Report report = new Report(itemname, itemproblem, floor, roomnumber, importancy, comments);
            report.setBuildingID(Integer.parseInt(buildingID));
            try {
                ReportMapper.insertReport(report);
                System.out.println("insert report");
                ReportMapper.insertBlob(report, fileParts);
            } catch (PolygonException ex) {
                req.getSession().setAttribute("errorMsg", ex.getMessage());
            }
        } else {
            String msg = "udfyld venligst de krævede felter";
            throw new PolygonException(msg);
        }

    }

    private void prepareReportList(HttpServletRequest req) {
        List<Report> reports = null;
        try {
            reports = ReportMapper.getReports();
        } catch (PolygonException ex) {
            req.getSession().setAttribute("errorMsg", ex.getMessage());
        }
        req.getSession().setAttribute("reports", reports);
    }

    private void prepareBuildingList(HttpServletRequest req) {
        List<Building> buildings = null;
        String ownerName = (String) req.getSession().getAttribute("logged_in_name");
        String ownerType = (String) req.getSession().getAttribute("logged_in_type");
        try {
            buildings = BuildingMapper.getBuildings(ownerName, ownerType);
        } catch (PolygonException ex) {
            req.getSession().setAttribute("errorMsg", ex.getMessage());
        }
        req.getSession().setAttribute("buildings", buildings);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String do_this = req.getParameter("do_this");

        switch (do_this) {
            case "delete":
                removeReport(req);
                prepareReportList(req);
                resp.sendRedirect("reportlist.jsp");
                break;

            case "add":
                try {
                    addReport(req);
                    prepareReportList(req);
                    resp.sendRedirect("reportlist.jsp");
                } catch (PolygonException ex) {
                    req.setAttribute("errorMsg", ex.getMessage());
                    rd = req.getRequestDispatcher("reportadd.jsp");
                    rd.forward(req, resp);
                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        prepareBuildingList(req);
        prepareReportList(req);
        resp.sendRedirect("reportlist.jsp");
    }

}
