/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rafeqfiad
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

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
            throws ServletException, IOException , FileNotFoundException, ClassNotFoundException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            
            if (isNullOrEmpty(userName)) {
                response.sendRedirect("ErrorServlet?error=Email is empty");
                return;
            }
            if (isNullOrEmpty(password)) {
                response.sendRedirect("ErrorServlet?error=Password is empty");
                return;
            }
            
            boolean sucessfullAuthentication = authenticateUser(userName, password, request);
            
            if (!sucessfullAuthentication) {
                response.sendRedirect("ErrorServlet?error=The User is not Authenticated.");
                return;
            }
            
            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
        }
    }
    
     private boolean authenticateUser(String userName, String password, HttpServletRequest request) throws IOException, FileNotFoundException, ClassNotFoundException {
        ServletContext sc = getServletContext();
        String contextPath=sc.getRealPath(File.separator);
        HttpSession session = request.getSession();
        
        DBO dbo = new DBO();
        List<Student> users = dbo.getAllStudents();
        if (users == null) {
            return false;
        }

        for (Student user : users) {
            if (user.getEmail().equals(userName) && user.getPassword().equals(password)) {
                session.setAttribute("USER", user);
                List<Borrow> Borrows = dbo.getAllBorrow(user.getId());
                session.setAttribute("allBorrows",Borrows );
                session.setAttribute("AllBooks", dbo.getAllBooks());
                
                return true;
            }
        }
        return false;

    }
      public static boolean isNullOrEmpty(String s) {
        if (s == null) {
            return true;
        }
        return s.isEmpty();
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
            throws ServletException, IOException ,FileNotFoundException{
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException ,FileNotFoundException{
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
