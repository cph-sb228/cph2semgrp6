<%-- 
    Document   : index--
    Created on : Apr 3, 2016, 10:41:34 PM
    Author     : terfy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="addbuilding" method="POST">
            <p>Company name</p>
            <input type="text" name="owner" /></br>
            <p>Street</p>
            <input type="text" name="address" /></br>
            <p>Building nr.</p>
            <input type="text" name="housenr" /></br>
            <p>Zipcode</p>
            <input type="text" name="zipcode" /></br>
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