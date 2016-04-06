<%-- 
    Document   : usersadd
    Created on : Apr 6, 2016, 10:47:33 AM
    Author     : terfy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>user add</title>
    </head>
    <body>
        <h1>User add</h1>
        <form action="adduser" method="POST">
            <p>User name</p>
            <input type="text" name="username" /></br>
            <p>Password</p>
            <input type="password" name="password" /></br>
            <input type="password" name="password2" />bekræft kode</br>
            <p>Email adresse</p>
            <input type="text" name="email" /></br>
            <input type="hidden" name="do_this" value="add" />
            <input type="submit" name="submit" value="Add user"/>
        </form>
    </body>
</html>
