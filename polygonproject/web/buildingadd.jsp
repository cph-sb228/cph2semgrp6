<%-- 
    Document   : buildingadd.jsp
    Created on : Apr 6, 2016, 10:45:49 AM
    Author     : terfy
--%>

<% String ownerType = (String) request.getSession().getAttribute("logged_in_type"); %>
<% if (ownerType==null || ownerType.equals("")){
    response.sendRedirect("Login");
} %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Building add</title>
    </head>
    <body>
        <a href="menu">Tilbage til menu</a>
        <a href="Logout">Log ud</a><br />
        <h1>Building add</h1>
        <form action="addbuilding" method="POST">
            <p>Street</p>
            <input type="text" name="address" /></br>
            <p>Building nr.</p>
            <input type="text" name="housenr" /></br>
            <p>Zipcode</p>
            <input type="text<%--number, men nej tak til pilene--%>" name="zipcode" /></br>
            <p>City</p>
            <input type="text" name="city" /></br>
            <p>Floor nr.</p>
            <input type="text" name="floor" /></br>
            <p>Square meters</p>
            <input type="text" name="km2" /></br>
            <p>Building condition</p>
            <input type="text" name="conditions" list="conditionlist" />
            <datalist id="conditionlist">
                <option value="Good">
                <option value="Fair">
                <option value="Bad">
            </datalist></br></br>
            <input type="hidden" name="do_this" value="add" />
            <input type="submit" name="submit" value="Add building"/>
        </form>
    </body>
</html>