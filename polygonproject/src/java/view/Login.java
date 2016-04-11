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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author terfy
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    private List<User> users;

    private boolean checkUserType(HttpServletRequest req, List<Users> users){
        return true;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, List<User> users) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.length() > 0 && password.length()> 0){
            for(User user : users){
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    request.getSession().setAttribute("logged_in_name", username);
                    request.getSession().setAttribute("logged_in_type", user.getType());
                    response.sendRedirect("index.jsp");
                } else response.sendRedirect("Login");

            }
        } else response.sendRedirect("Login");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        users = Users.getUsers();
        processRequest(request, response, users);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
