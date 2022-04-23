<%-- 
    Document   : Error_login
    Created on : Feb 28, 2021, 1:13:43 AM
    Author     : rafeqfiad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LoginPage</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel='stylesheet' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
        <link rel="stylesheet" type="text/css" href="CSS/loginCSS.css">
    </head>
    <%  String error = (String) request.getAttribute("error");
        String errorElement = "";

        if (error != null) {
            errorElement = "<div style='color: red'>" + "**" + error + "</div>";
        } else {
            errorElement = "<h6 style='color: red'>**There was an error, please try again.</h6>";
        }

        String previousPage = request.getParameter("prevPage");
        if (previousPage != null) {
            errorElement += "<br/>";
            errorElement += "<form action=\"" + previousPage + "\" method=\"POST\">";
            //errorElement += "<input type=\"submit\" value=\"Previous Page\"/>";
            errorElement += "</form>";
        }%>
    <body>
         </div>
        <div class="wrapper">
            <form class="aasd" method="POST" action="index.html">
                <br/>
                <br/>
                <h2 class="form-signin-heading"><%= errorElement%> </h2>
                <br/>
                <br/>
                <h5 style='color:red'> *Please Try again</h5>
                <button class="btn btn-lg btn-primary btn-block" type="submit">GO To Login Page</button>
            </form>
        </div>
    </body>
</html>
