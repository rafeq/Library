<%@page import="Model.Student"%>
<%@page import="Model.Borrow"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ReturnBook Page</title>
        <link rel='stylesheet' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>

        <link rel="stylesheet" type="text/css" href="CSS/style_ReturnPage.css" />
    </head>
    <body class="bg">
        <jsp:useBean scope="session" id="allBorrows" class="List<Model.Borrow>"></jsp:useBean>
       
        <jsp:useBean scope="session" id="USER" class="Model.Student"></jsp:useBean>

            <div id="search-bg"></div>
            <div class="wrapper">

                <form method="POST" action="LogoutServlet"><button  class="btn btn-xs " type="submit">Logout <br>(<% USER.getFirstName();%> <%USER.getLastName();%>)</button></form>            <br>
            <br>
            <br>
            <div class="wrapper1">
                

            </div>

            <form method="POST" action="ReturnServlet" id="form1">
                <section class="">
                    <div class="container1">
                        <table>

                            <tbody>

                                <%
                                    int k = 0;
                                    for (int i = 0; i < allBorrows.size();) {
                                        k++;
                                %><tr><%
                                    for (; (i < 3 * k) && (i < allBorrows.size()); i++) {
                                    %><td>
                                        <label class="checkbox">                                            
                                            <input class="checkbox" type="checkbox" name="checkbox<%= i%>" value="checkbox"<%= allBorrows.get(i).getBookName()%>  >
                                            <h5 style=' font-weight: normal'> <%= allBorrows.get(i).getBookName()%> </h5>
                                        </label>
                                    </td>
                                    <% } %>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                </section>
                <button class="btt btn btn-lg btn-primary " type="submit">החזר</button>
                <h5 class="hh" style='color: red'></h5>            
            </form>
        </div>
    </body>
</html>
