<%-- 
    Document   : usersadd
    Created on : Apr 6, 2016, 10:47:33 AM
    Author     : terfy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String ownerType = (String) request.getSession().getAttribute("logged_in_type"); %>
<% if (ownerType==null || ownerType.equals("")){
    response.sendRedirect("Login");
} %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>user add</title>
    </head>
    <body>
        <a href="menu">Tilbage til menu</a>
        <a href="Logout">Log ud</a><br />
        <h1>User add</h1>
        <form action="adduser" method="POST">
            <p>User name</p>
            <input type="text" name="username" /></br>
            <p>Password</p>
            <input type="password" name="password" /></br>
            <input type="password" name="password2" />bekræft kode</br>
            <p>Email adresse</p>
            <input type="text" name="email" /></br>
            <p>Login type</p>
            <input type="text" name="type" /></br>
            <input type="hidden" name="do_this" value="add" />
            <input type="submit" name="submit" value="Add user"/>
        </form>
    </body>
</html>
