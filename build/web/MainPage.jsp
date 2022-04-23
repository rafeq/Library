<%-- 
    Document   : MainPage
    Created on : Feb 28, 2021, 12:58:34 AM
    Author     : rafeqfiad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
        <link rel="stylesheet" type="text/css" href="CSS/MainPageCSS.css">
        <link rel="stylesheet" type="text/css" href="CSS/style_Main.css" /> 
    </head>
    <body class="bg">
        <div id="search-bg"></div>
        <div class="wrapper">
            <form class="button_2" action="BorrowBook.jsp">
                <input type="image" src="https://icons.iconarchive.com/icons/kyo-tux/soft/128/Book-icon.png" alt="Button"  class="button_2">
                <h2 class="button2-text">BorrowBook</h2>
            </form>


            <form class="button_3" action="ReturnBook.jsp">
                <input type="image" src="https://icons.iconarchive.com/icons/robinweatherall/library/256/books-icon.png" alt="Button"  class="button_3">
                <h2 class="button3-text">ReturnBook</h2>
            </form>   


            <form class="button_4" action="Search.jsp">
                <input type="image" src="https://icons.iconarchive.com/icons/jommans/mushroom/256/Search-icon.png" alt="Button"  class="button_4">
                <h2 class="button4-text">SearchBook</h2>
            </form>   

        </div>
    </body>
</html>
