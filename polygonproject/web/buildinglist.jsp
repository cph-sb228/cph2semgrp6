<%-- 
    Document   : newjsp
    Created on : Apr 4, 2016, 9:37:59 AM
    Author     : terfy
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.Building"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Building> buildings = ((ArrayList<Building>) request.getSession().getAttribute("buildings")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Bygning liste</title>
    </head>
    <body>
        <h1>Liste over bygninger</h1>
        <table>
            <th>Company</th><th>Address</th>
        <%
            for (int i = 0; i < buildings.size(); i++) {
                out.print("<tr>");                
                out.print("<td>");
                out.print(buildings.get(i).getOwner());
                out.print("</td>");
                out.print("<td>");
                out.print(buildings.get(i).getAddress());
                out.print("</td>");
                out.print("<td>");
                %>
            <form action="deletebuilding" method="post">   
                <input type="submit" name="delete" value="Delete"/>
                <input type ="hidden" name="id" value="<%= buildings.get(i).getId() %>"/>
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
