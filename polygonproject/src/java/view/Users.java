/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsersMapper;

/**
 *
 * @author terfy
 */
public class Users extends HttpServlet {
    
    public static List<User> getUsers() {
        List<User> users = null;
        try {
            users = UsersMapper.getUser();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    private void removeUser(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            UsersMapper.removeUser(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean addUser(HttpServletRequest req) {
        //int id = Integer.parseInt(req.getParameter("id"));
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");
        String password2 = (String) req.getParameter("password2");
        String email = (String) req.getParameter("email");
        String type = (String) req.getParameter("type");
        if (username.length() > 0 && password.length() > 0 && password.equals(password2) && type.length() > 0) {
            User user = new User(username, password, email, type);
            try {
                UsersMapper.insertUser(user);
                return true;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private void prepareUserList(HttpServletRequest req) {
        List<User> users = null;
        try {
            users = UsersMapper.getUser();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.getSession().setAttribute("users", users);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String do_this = req.getParameter("do_this");

        switch (do_this) {
            case "delete":
                removeUser(req);
                prepareUserList(req);
                resp.sendRedirect("userslist.jsp");
                break;

            case "add":
                if (addUser(req)) {
                    prepareUserList(req);
                    resp.sendRedirect("userslist.jsp");
                } else {
                    resp.sendRedirect("usersadd.jsp");
                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("logged_in_type") != null 
                && req.getSession().getAttribute("logged_in_type").equals("polygon")){
            prepareUserList(req);
            resp.sendRedirect("userslist.jsp");
        } else {
            resp.sendRedirect("index.html");
        
        }
    }

}
