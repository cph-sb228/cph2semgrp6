<%-- 
    Document   : reportadd
    Created on : Apr 6, 2016, 2:27:41 PM
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
        <title>report add</title>
    </head>
    <body>
        <h1>report add</h1>
        <form action="addreport" method="POST">
            <p>Vælg fil(er)</p>
            <input type="file" name="file" /></br>
            <p>Choose building</p>
            <select name="building">
                <% for(Building b : buildings){ %>
                    <option value="<%= b.getId() %>" selected ><%= b.getOwner() %></option>
                <% } %>
                
            </select>
            <input type="password" name="password" /></br>
            <input type="password" name="password2" />bekræft kode</br>
            <p>Email adresse</p>
            <input type="text" name="email" /></br>
            <input type="hidden" name="do_this" value="add" />
            <input type="submit" name="submit" value="Add user"/>
        </form>
    </body>
</html>