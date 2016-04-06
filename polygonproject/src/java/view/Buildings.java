/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Building;
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

    private boolean addBuilding(HttpServletRequest req) {
        //int id = Integer.parseInt(req.getParameter("id"));
        String a,b = "";
        String owner = (String) req.getParameter("owner");
        String address = (String) req.getParameter("address");
        if (owner.length() > 0 && address.length() > 0) {
            Building building = new Building(owner, address);
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
        try {
            buildings = BuildingMapper.getBuildings();
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
                if(addBuilding(req)){
                prepareBuildingList(req);
                resp.sendRedirect("buildinglist.jsp");
                } else resp.sendRedirect("index.jsp");
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
