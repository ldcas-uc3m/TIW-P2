<%@
    page language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>

<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Error page</title>
    </head>
    <body>

        <%@ include file="Header.jsp" %>

        <h2>Error page</h2>

        <%
        if(exception != null) {
        %>
            <%= exception.getClass() %>
            <%= exception.getMessage() %>
        <%
        }
        %>

        <%
        if (response.getStatus() != HttpServletResponse.SC_OK) {
        %>
            ERROR <%=response.getStatus() %>
        <%
        }
        %>

        <%= request.getAttribute("message") %>


        <%@ include file="Footer.jsp" %>

    </body>
</html>