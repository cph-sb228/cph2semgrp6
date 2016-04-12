<%-- 
    Document   : userslist
    Created on : Apr 6, 2016, 9:51:17 AM
    Author     : terfy
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String ownerType = (String) request.getSession().getAttribute("logged_in_type"); %>
<% if (ownerType==null || ownerType.equals("")){
    response.sendRedirect("Login");
} %>

<% List<User> users = ((ArrayList<User>) request.getSession().getAttribute("users")); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>User liste</title>
    </head>
    <body>
        <h1>Liste over brugere</h1>
        <a href="usersadd.jsp">Add user</a>
        <table>
            <th>Username</th><th>Password</th><th>Email</th>
        <%
            for (int i = 0; i < users.size(); i++) {
                out.print("<tr>");                
                out.print("<td>");
                out.print(users.get(i).getUsername());
                out.print("</td>");
                out.print("<td>");
                out.print(users.get(i).getPassword());
                out.print("</td>");
                out.print("<td>");
                out.print(users.get(i).getEmail());
                out.print("</td>");
                out.print("<td>");
                %>
            <form action="deleteuser" method="post">   
                <input type="submit" name="delete" value="Delete"/>
                <input type ="hidden" name="id" value="<%= users.get(i).getId() %>"/>
                <input type="hidden" name="do_this" value="delete" />
                </form>
                <%
                out.print("</td>");
                out.print("</tr>");
                              
            }
        %>


        </table>
    </body>
</html>
