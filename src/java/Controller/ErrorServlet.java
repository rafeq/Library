/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rafeqfiad
 */
@WebServlet(name = "ErrorServlet", urlPatterns = {"/ErrorServlet"})
public class ErrorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            String head = " <head>"
                    + "<title>LoginPage</title>"
                    + "<meta charset=\"UTF-8\">"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                    + "<link rel=\'stylesheet\' href=\'http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css\'>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/loginCSS.css\">"
                    + "</head>";
            out.println(head);

            
            String error = (String)request.getAttribute("error");
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
            }

            String body = "<body class=\"bg\">"
                    + "<div id=\"search-bg\"></div>"
                    + "<div class=\"wrapper\">"
                    + "<form class=\"aasd\" method=\"POST\" action=\"index.html\">"
                    + "<br/>"
                    + "<br/>"
                    + "<h2 class=\"form-signin-heading\">" + errorElement + "</h2>"
                    + "<br/>"
                    + "<br/>"
                    + "<h5 style='color:red'> *Please Try again</h5>"
                    
                    + "<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">GO To Login Page</button>"
                    + "</form>"
                    + "</div>"
                    + "</body>";
            out.println(body);
            out.println("</html>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
