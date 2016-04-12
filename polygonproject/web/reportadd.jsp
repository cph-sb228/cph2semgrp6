<%-- 
    Document   : reportadd
    Created on : Apr 6, 2016, 2:27:41 PM
    Author     : terfy
--%>

<%@page import="controller.Report"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.Building"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String ownerType = (String) request.getSession().getAttribute("logged_in_type"); %>
<% if (ownerType==null || ownerType.equals("")){
    response.sendRedirect("Login");
} %>

<% List<Building> buildings = ((ArrayList<Building>) request.getSession().getAttribute("buildings")); %>
<% List<Report> reports = ((ArrayList<Report>) request.getSession().getAttribute("reports")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>report add</title>
    </head>
    <body>
        <h1>report add</h1>
        <form action="addreport" method="POST">

            <p>Vælg fil(er)</p>
            <input type="file" name="file" /></br>

            <p>Choose building and condition</p>
            <select name="buildingID">
            <% for(Building b : buildings){ %>
                <option value="8" selected >8 fat det</option>
                <option value="<%= b.getId() %>" ><%= b.getOwner() %></option>
            <% } %>
            </select>

            </br>
            <p>Itemname<br />
              <input type="text" name="itemname" /></p>
            <p>Item Problem<br />
              <input type="text" name="itemproblem" /></p>
            <p>Floor<br />
              <input type="text" name="floor" /></p>
            <p>Room Number<br />
              <input type="text" name="roomnumber" /></p>
            <p>Importancy<br />
                <input type="text" name="importancy" /></p>
            <p>Comments<br />
                <textarea name="comments"></textarea></p>

            <input type="hidden" name="do_this" value="add" />
            <input type="submit" name="submit" value="Add report"/>
        </form>
    </body>
</html>