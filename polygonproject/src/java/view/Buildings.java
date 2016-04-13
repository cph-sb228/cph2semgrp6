/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Building;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BuildingMapper;

/**
 *
 * @author terfy
 */
public class Buildings extends HttpServlet {

    private void removeBuilding(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            BuildingMapper.removeBuilding(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addBuildingFloorplan(File file, String path, String filename){
        
    }

    private boolean addBuilding(HttpServletRequest req) {
        String owner = (String) req.getSession().getAttribute("logged_in_name");
        String address = (String) req.getParameter("address");
        String housenr = (String) req.getParameter("housenr");
        String zipcode = (String) req.getParameter("zipcode");
        String city = (String) req.getParameter("city");
        String floor = (String) req.getParameter("floor");
        String km2 = (String) req.getParameter("km2");
        String conditions = (String) req.getParameter("conditions");
        if (address.length() > 0
                && housenr.length() > 0
                && zipcode.length() > 0
                && city.length() > 0
                && floor.length() > 0
                && km2.length() > 0
                && conditions.length() > 0) {
            if (!housenr.matches("[0-9]+") || !zipcode.matches("[0-9]+") || !floor.matches("[0-9]+") || !km2.matches("[0-9]+")) {
                return false;
            }
            Building building = new Building(owner, address, Integer.valueOf(housenr), Integer.valueOf(zipcode), city, Integer.valueOf(floor), Integer.valueOf(km2), conditions);
            try {
                BuildingMapper.insertBuilding(building);
                return true;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
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
                removeBuilding(req);
                prepareBuildingList(req);
                resp.sendRedirect("buildinglist.jsp");
                break;

            case "add":
                if (addBuilding(req)) {
                    prepareBuildingList(req);
                    resp.sendRedirect("buildinglist.jsp");
                } else {
                    resp.sendRedirect("buildingadd.jsp");
                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareBuildingList(req);
        resp.sendRedirect("buildinglist.jsp");
    }

}
