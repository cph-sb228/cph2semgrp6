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

    private void removeBuilding(HttpServletRequest req) throws ClassNotFoundException {
        int id = Integer.parseInt(req.getParameter("id"));
        BuildingMapper.removeBuilding(id);
    }

    private void addBuilding(HttpServletRequest req) {
        //int id = Integer.parseInt(req.getParameter("id"));
        String owner = (String) req.getParameter("owner");
        String address = (String) req.getParameter("address");
        Building building = new Building(owner, address);
        try {
            BuildingMapper.insertBuilding(building);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                if (req.getParameter("delete") != null && req.getParameter("delete").equals("Delete")) {
                    System.out.println("DELETE knap trykket");
                    try {
                        removeBuilding(req);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                resp.sendRedirect("buildinglist.jsp");
                break;

            case "add":
                addBuilding(req);
                prepareBuildingList(req);
                resp.sendRedirect("buildinglist.jsp");
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
