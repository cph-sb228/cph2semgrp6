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
<% String ownerType = (String) request.getSession().getAttribute("logged_in_type"); %>
<% if (ownerType==null || ownerType.equals("")){
    response.sendRedirect("Login");
} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Bygning liste</title>
    </head>
    <body>
        <a href="menu">Tilbage til menu</a>
        <a href="Logout">Log ud</a><br />
        <h1>Liste over bygninger</h1>
        <% if (!ownerType.equals("polygon")){ %>
        <a href="buildingadd.jsp">Add building</a>
        <% ;} %>
        <table>
            <th>Company </th><th>Street </th><th>House nr.  </th><th>Zipcode    </th><th>City   </th><th>Floor  </th><th>Square meters  </th><th>Condition</th><th>Floorplan</th>
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
                out.print(buildings.get(i).getHousenr());
                out.print("</td>");
                out.print("<td>");
                out.print(buildings.get(i).getZipcode());
                out.print("</td>");
                out.print("<td>");
                out.print(buildings.get(i).getCity());
                out.print("</td>");
                out.print("<td>");
                out.print(buildings.get(i).getFloor());
                out.print("</td>");
                out.print("<td>");
                out.print(buildings.get(i).getKm2());
                out.print("</td>");
                out.print("<td>");
                out.print(buildings.get(i).getConditions());
                out.print("</td>");
                out.print("<td>");
                out.print(buildings.get(i).getBlobname());
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
