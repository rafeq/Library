/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ReturnServlet", urlPatterns = {"/ReturnServlet"})
public class ReturnServlet extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();

            Student stu = (Student) session.getAttribute("USER");
            String StudentEmail = stu.getEmail();

            List<String> cp = new ArrayList<String>();

            List<Borrow> bookBorrow = (ArrayList<Borrow>) session.getAttribute("allBorrows");
            DBO dbo = new DBO();
            for (int i = 0; i < bookBorrow.size(); i++) {
                if (request.getParameter("checkbox" + i) != null) {
                    cp.add(bookBorrow.get(i).getBookName());
                    dbo.updatBook(bookBorrow.get(i).getCopy_book(), +1);
                    dbo.DeleteBorrow(stu.getId(), bookBorrow.get(i).getCopy_book());
                    dbo.DeleteCopyBook(stu.getId(), bookBorrow.get(i).getCopy_book());

                    Date t = new Date();
                    int moreFine = 0;
                    if (t.after(bookBorrow.get(i).getUntil_date())) {
                        Date DD = new Date();
                        long diff = DD.getTime() - bookBorrow.get(i).getUntil_date().getTime();
                        moreFine = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    }

                    dbo.updatStudentFine(moreFine + stu.getFine());

                }
            }

            String errorMessage = "";

            for (Borrow b : bookBorrow) {
                errorMessage = "Book Name: " + b.getBookName() + " ISBN: " + b.getCopy_book() + "<br>";
            }

            errorMessage = errorMessage + "are Returned sucssesfuly";

            List<Borrow> Borrows = dbo.getAllBorrow(stu.getId());
            session.setAttribute("allBorrows", Borrows);
            List<Book> books = dbo.getAllBooks();
            session.setAttribute("AllBooks", books);

            request.setAttribute("MSG", errorMessage);
            request.setAttribute("FailOrSuccess", "true");
            request.getRequestDispatcher("ReturnBook.jsp").forward(request, response);
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
            Logger.getLogger(ReturnServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReturnServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
