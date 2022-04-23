<%-- 
    Document   : BorrowBook
    Created on : Feb 28, 2021, 1:02:30 AM
    Author     : rafeqfiad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean scope="session" id="USER" class="Model.Student"></jsp:useBean>
    <%
        String userName = USER.getFirstName() + " " + USER.getLastName();
    %>
    <head>
        <title>Borrow A Book</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
        <link rel="stylesheet" type="text/css" href="CSS/LoanBookCSS.css">
        <link rel="stylesheet" type="text/css" href="CSS/style_Loan.css" /> 
    </head>
    <body>
        <div id="search-bg"></div>
        <div class="wrapper">
            
            <form method="POST" action="LogoutServlet">
                <button class="btn btn-xs " type="submit">Logout <br>(<%=userName%>)</button> 
            </form>
            <br>
            <br>
            <br>
            <h2 class="form-signin-heading">השאלת ספר</h2>
            <br>
            <div class="wrapper1">
                <form method="POST" action="BorrowServlet">
                    <input type="text" class="form-control" name="ISBN" placeholder="הכנס מספר ספר" autofocus="" >
                    <br>
                    <button method="POST" action="BorrowServlet" class="btn btn-lg btn-primary btn-block" type="submit">השאל</button>
                </form>
                <h5 style='color: #00ff00'>successfully</h5>
            </div>

        </div>
    </body>
</html>
