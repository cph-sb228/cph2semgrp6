<%-- 
    Document   : buildingadd.jsp
    Created on : Apr 6, 2016, 10:45:49 AM
    Author     : terfy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Building add</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="addbuilding" method="POST">
            <p>Company name</p>
            <input type="text" name="owner" /></br>
            <p>Building address</p>
            <input type="text" name="address" /></br>
            <input type="hidden" name="do_this" value="add" />
            <input type="submit" name="submit" value="Add building"/>
        </form>
    </body>
</html>