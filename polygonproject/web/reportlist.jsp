<%-- 
    Document   : reportlist.jsp
    Created on : Apr 7, 2016, 9:23:12 AM
    Author     : terfy
--%>

<%@page import="controller.Report"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String ownerType = (String) request.getSession().getAttribute("logged_in_type"); %>
<% if (ownerType==null || ownerType.equals("")){
    response.sendRedirect("Login");
} %>

<% List<Report> reports = ((ArrayList<Report>) request.getSession().getAttribute("reports")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Report liste</title>
    </head>
    <body>
        <h1>Liste over reports</h1>
        <a href="reportadd.jsp">Add report</a>
        <table>
            <th>Building ID</th><th>Item name</th><th>Item Problem</th><th>Floor</th>
            <th>Room Number</th><th>Importancy</th><th>Comments</th>
                <%
                    for (int i = 0; i < reports.size(); i++) {
                        out.print("<tr>");
                        out.print("<td>");
                        out.print(reports.get(i).getBuildingID());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(reports.get(i).getItemname());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(reports.get(i).getItemproblem());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(reports.get(i).getFloor());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(reports.get(i).getRoomnumber());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(reports.get(i).getImportancy());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(reports.get(i).getComments());
                        out.print("</td>");
                        out.print("<td>");
                        
                %>
            <form action="deletereport" method="post">   
                <input type="submit" name="delete" value="Delete"/>
                <input type ="hidden" name="id" value="<%= reports.get(i).getId()%>"/>
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