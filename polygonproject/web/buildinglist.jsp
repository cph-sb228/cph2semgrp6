<%-- 
    Document   : newjsp
    Created on : Apr 4, 2016, 9:37:59 AM
    Author     : terfy
--%>

<%@page import="controller.Building"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bygning liste</title>
    </head>
    <body>
        <h1>Liste over bygninger</h1>
        
        <%= request.getSession().getAttribute("buildings") %>
        
        
        
    </body>
</html>
