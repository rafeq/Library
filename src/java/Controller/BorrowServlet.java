/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "BorrowServlet", urlPatterns = {"/BorrowServlet"})
public class BorrowServlet extends HttpServlet {

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
            throws ServletException, IOException,ClassNotFoundException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            int BookID = Integer.parseInt(request.getParameter("ISBN"));

            
            HttpSession session = request.getSession();

            Student stu = (Student) session.getAttribute("USER");
            DBO dbo = new DBO();

            String errorMessage = "";

            Student X = (Student) session.getAttribute("USER");
            if (X == null) {
                errorMessage = "ERROR USER tru again later.";
                request.setAttribute("MSG", errorMessage);
                request.setAttribute("FailOrSuccess", "false");
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BorrowBookServlet");
                disp.forward(request, response);
                return;
            }

            Book Y = dbo.getBook(BookID);
            if (Y == null) {
                errorMessage = "ERROR enter , try another book id .";
                request.setAttribute("MSG", errorMessage);
                request.setAttribute("FailOrSuccess", "false");
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BorrowServlet");
                disp.forward(request, response);
                return;
            }

            boolean flag = false;
            if (Y != null) {
                if (Y.getCopies() <= 0) {
                    flag = true;
                }
            }
            if (flag) {
                errorMessage = "There is no books to borrow";
                request.setAttribute("MSG", errorMessage);
                request.setAttribute("FailOrSuccess", "false");
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BorrowServlet");
                disp.forward(request, response);
                return;
                //response.sendRedirect("/LoanBookServlet?error=" + errorMessage);
            }

            if (Y == null || !errorMessage.equals("") || X.getFine() >= 100) {
                errorMessage = "Book ID , cannot contain characters <BR>";
                if (X != null && X.getFine() >= 100) {
                    errorMessage = "Your fine is 100+ ! <BR>"
                            + "first of all you have to pay it" + errorMessage;
                }
                //response.sendRedirect("LoanBookServlet?error=" + errorMessage);
                request.setAttribute("MSG", errorMessage);
                request.setAttribute("FailOrSuccess", "false");
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BorrowServlet");
                disp.forward(request, response);
                return;
            }

            dbo.addBorrow(Y.getIsbn(),Y.getBookName(), X.getId(), new Date(), new Date());
            dbo.updatBook(Y.getIsbn(),-1);
            
            

            errorMessage = "Book laoned successfully ,you have 3 months to recieve it back";

            // response.sendRedirect("LoanBookServlet?MSG=" + errorMessage);
            List<Borrow> Borrows = dbo.getAllBorrow(stu.getId());
            session.setAttribute("allBorrows", Borrows);
            List<Book> books = dbo.getAllBooks();
            session.setAttribute("AllBooks", books);
            
            request.setAttribute("MSG", errorMessage);
            request.setAttribute("FailOrSuccess", "true");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/BorrowBook.jsp");
            disp.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrowServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrowServlet.class.getName()).log(Level.SEVERE, null, ex);
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
