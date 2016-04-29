package presentation;

import domain.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.PolygonException;
import dataaccess.UsersMapper;

/**
 *
 * @author terfy
 */
public class Users extends HttpServlet {

    RequestDispatcher rd = null;

    public static List<User> getUsers() throws PolygonException {
        List<User> users = null;
        try {
            users = UsersMapper.getUser();
        } catch (PolygonException ex) {
            String msg = "fejl i getUser()";
            throw new PolygonException(msg);
        }
        return users;
    }

    //Upon pressing delete, chosen user's ID will be sent to the user mapper
    private void removeUser(HttpServletRequest req) throws PolygonException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            UsersMapper.removeUser(id);
        } catch (PolygonException ex) {
            String msg = "UsersMapper.removeUser() fails";
            throw new PolygonException(msg);
        }
    }

    //By filling the form and submiting, the info will be sent to the user mapper 
    private void addUser(HttpServletRequest req) throws PolygonException {
        //int id = Integer.parseInt(req.getParameter("id"));
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");
        String password2 = (String) req.getParameter("password2");
        String email = (String) req.getParameter("email");
        String type = (String) req.getParameter("type");
        if (username.length() > 0 && password.length() > 0 && password.equals(password2) && type.length() > 0) {
            User user = new User(username, password, email, type);
            UsersMapper.insertUser(user);
        } else {
            String msg = "Udfyld alle krævede felter";
            throw new PolygonException(msg);
        }
    }

    private void prepareUserList(HttpServletRequest req) throws PolygonException {
        List<User> users = null;
        try {
            users = UsersMapper.getUser();
        } catch (PolygonException ex) {
            String msg = "kunne ikke hente users via getUser()";
            throw new PolygonException(msg);
        }
        req.getSession().setAttribute("users", users);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String do_this = req.getParameter("do_this");

        switch (do_this) {
            case "delete":
                try {
                    removeUser(req);
                    prepareUserList(req);
                    resp.sendRedirect("userslist.jsp");
                } catch (PolygonException ex) {
                    req.setAttribute("errorMsg", ex.getMessage());
                    rd = req.getRequestDispatcher("userslist.jsp");
                    rd.forward(req, resp);
                }
                break;

            case "add":
                try {
                    addUser(req);
                    prepareUserList(req);
                    resp.sendRedirect("userslist.jsp");
                } catch (PolygonException ex) {
                    req.setAttribute("errorMsg", ex.getMessage());
                    rd = req.getRequestDispatcher("usersadd.jsp");
                    rd.forward(req, resp);
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (req.getSession().getAttribute("logged_in_type") != null
                && req.getSession().getAttribute("logged_in_type").equals("polygon")) {
            try {
                prepareUserList(req);
                resp.sendRedirect("userslist.jsp");
            } catch (PolygonException ex) {
                req.setAttribute("errorMsg", ex.getMessage());
                resp.sendRedirect("userslist.jsp");
            }
        } else {
            resp.sendRedirect("index.html");

        }
    }

}
