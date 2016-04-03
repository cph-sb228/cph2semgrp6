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
            <p>Building address</p>
            <input type="text" name="address" /></br>
            <input type="submit" name="submit" value="Add building"/>
        </form>
    </body>
</html>