<%@page import="Model.Student"%>
<%@page import="Model.Book"%>
<%@page import="Model.Borrow"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>        
        <link rel="stylesheet" type="text/css" href="CSS/SearchCSS.css">        
        <link rel="stylesheet" type="text/css" href="CSS/style_Search.css" />  
        <title>Search Page</title>
    </head>
    <body>
       
        <jsp:useBean scope="session" id="AllBooks" class="List<Model.Book>"></jsp:useBean>
        <jsp:useBean scope="session" id="USER" class="Model.Student"></jsp:useBean>

            <div id="search-bg"></div>
            <div class="wrapper">
            <% String userName = USER.getFirstName() + " " + USER.getLastName();%>
            <form method="POST" action="LogoutServlet">
                <button  class="btn btn-xs " type="submit">Logout <br>(<%=userName%>)</button>
            </form>    
            <br>
            <br>
            <br>
            <div class="wrapper1">
                
                <h2 class="form-signin-heading">חיפוש</h2>
                <br>
                <form method="POST" action="DoSearchServlet">                    
                    <input type="text" class="form-control" name="BookName" placeholder="הכנס שם ספר או סופר" autofocus="" >
                    <br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">חפש</button>
                </form>
            </div>

            <form id="form1">
                <section class="">
                    <div class="container1">
                        <table>
                            <thead>
                                <tr class="header">
                                    <th>ISBN<div>ISBN</div></th>
                                    <th>Book Name<div>Book Name</div></th>
                                    <th>Author Name<div>Author Name</div></th>
                                    <th>*Quantity*<div>*Quantity*</div></th>
                                </tr>
                            </thead>                           
                            <tbody>
                                <%

                                    if (AllBooks != null) {
                                        for (int i = 0; i < AllBooks.size(); i++) {
                                %><tr>
                                    <td> <%= AllBooks.get(i).getIsbn()%> </td>
                                    <td> <%= AllBooks.get(i).getBookName()%> </td>
                                    <td> <%= AllBooks.get(i).getAuthorName()%> </td>
                                    <td> <%= AllBooks.get(i).getCopies()%></td>
                                </tr>
                                <%}
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </section>
            </form>
        </div>
    </body>
</html>
