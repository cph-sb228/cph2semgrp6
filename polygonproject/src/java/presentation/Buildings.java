package presentation;

import domain.Building;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
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

/**
 *
 * @author terfy
 */
@MultipartConfig
public class Buildings extends HttpServlet {

    RequestDispatcher rd = null;

    private void removeBuilding(HttpServletRequest req) throws PolygonException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            BuildingMapper.removeBuilding(id);
        } catch (PolygonException ex) {
            String msg = "kunne ikke slette bygning.";
            throw new PolygonException(msg);
        }
    }
    
    private void addBuilding(HttpServletRequest req) throws PolygonException {
        String owner = (String) req.getSession().getAttribute("logged_in_name");
        String address = (String) req.getParameter("address");
        String housenr = (String) req.getParameter("housenr");
        String zipcode = (String) req.getParameter("zipcode");
        String city = (String) req.getParameter("city");
        String floor = (String) req.getParameter("floor");
        String km2 = (String) req.getParameter("km2");
        String conditions = (String) req.getParameter("conditions");
        List<Part> fileParts = Collections.emptyList();

        try {
            if (req.getPart("file").getSize() > 0) {
                fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
            }
        } catch (IOException | ServletException ex) {
            String msg = "fejl i filepart";
            throw new PolygonException(msg);
        }

        if (address.length() > 0
                && housenr.length() > 0
                && zipcode.length() > 0
                && city.length() > 0
                && floor.length() > 0
                && km2.length() > 0
                && conditions.length() > 0) {
            if (!housenr.matches("[0-9]+") || !zipcode.matches("[0-9]+") || !floor.matches("[0-9]+") || !km2.matches("[0-9]+")) {
                System.out.println("");
                String msg = "Housenr, zipcode, floor og km2 må kun være hele tal";
                throw new PolygonException(msg);
            }
            Building building = new Building(owner, address, Integer.valueOf(housenr), Integer.valueOf(zipcode), city, Integer.valueOf(floor), Integer.valueOf(km2), conditions);
            String filename = "";

            if (fileParts.size() > 0) {
                Part file = fileParts.get(0);
                filename = file.getHeader("Content-Disposition");
                filename = filename.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1");
                building.setBlobname(filename);
            }
            try {
                BuildingMapper.insertBuilding(building, fileParts);
                System.out.println("insertbuilding try virker");
            } catch (PolygonException ex) {
                throw new PolygonException(ex.getMessage());
            }
        } else {
            String msg = "husk at udfylde alle krævede felter";
            throw new PolygonException(msg);
        }

    }

    private void prepareBuildingList(HttpServletRequest req) throws PolygonException {
        List<Building> buildings = null;
        String ownerName = (String) req.getSession().getAttribute("logged_in_name");
        String ownerType = (String) req.getSession().getAttribute("logged_in_type");
        try {
            buildings = BuildingMapper.getBuildings(ownerName, ownerType);
        } catch (PolygonException ex) {
            String msg = "kunne ikke lave liste af bygninger";
            throw new PolygonException(msg);
        }
        req.getSession().setAttribute("buildings", buildings);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String do_this = req.getParameter("do_this");
        System.out.println("--------" + do_this);
        switch (do_this) {

            case "delete":
                try {
                    removeBuilding(req);
                    prepareBuildingList(req);
                    resp.sendRedirect("buildinglist.jsp");
                } catch (PolygonException ex) {
                    req.setAttribute("errorMsg", ex.getMessage());
                    rd = req.getRequestDispatcher("buildinglist.jsp");
                    rd.forward(req, resp);
                }
                break;

            case "add":
                try {
                    addBuilding(req);
                    prepareBuildingList(req);
                    resp.sendRedirect("buildinglist.jsp");
                } catch (PolygonException ex) {
                    req.setAttribute("errorMsg", ex.getMessage());
                    rd = req.getRequestDispatcher("buildingadd.jsp");
                    rd.forward(req, resp);
                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            prepareBuildingList(req);
            resp.sendRedirect("buildinglist.jsp");
        } catch (PolygonException ex) {
            req.setAttribute("errorMsg", ex.getMessage());
            rd = req.getRequestDispatcher("buildinglist.jsp");
            rd.forward(req, resp);
        }
    }

}
