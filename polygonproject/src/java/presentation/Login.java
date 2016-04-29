package presentation;

import domain.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.PolygonException;

/**
 *
 * @author terfy
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    RequestDispatcher rd = null;
    private List<User> users;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, List<User> users) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String logged_in_type = "";
        
        //Runs through the list of users if both inputs weren't empty and checks if access will be allowed
        if (username.length() > 0 && password.length() > 0) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    logged_in_type = user.getType();
                    request.getSession().setAttribute("logged_in_name", username);
                    request.getSession().setAttribute("logged_in_type", user.getType());
                    break;
                }
            }
        }
        
        switch (logged_in_type) {
            case "customer":
                response.sendRedirect("menu_customers.html");
                break;
            case "polygon":
                response.sendRedirect("menu_polygon.html");
                break;
            default:
                response.sendRedirect("Login");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            users = Users.getUsers();
            processRequest(request, response, users);
        } catch (PolygonException ex) {
            request.setAttribute("errorMsg",ex.getMessage());
            rd = request.getRequestDispatcher("Login");
            rd.forward(request, response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
