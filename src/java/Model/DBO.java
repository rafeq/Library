/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafeqfiad
 */
public class DBO {

    public List<Student> getAllStudents() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ROOT.\"STUDENT\"");
            List<Student> res = new ArrayList<Student>();
            while (rs.next()) {
                Student ac = new Student();
                ac.setFirstName(rs.getString("firstName"));
                ac.setLastName(rs.getString("lastName"));
                ac.setId(rs.getLong("id"));
                ac.setEmail(rs.getString("Email"));
                ac.setFine(rs.getInt("fine"));
                ac.setPassword(rs.getString("password"));
                res.add(ac);
            }
            cn.close();
            return res;
        } catch (Throwable ex) {
            Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Borrow> getAllBorrow(long id) {
        long i=id;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ROOT.BORROW");
            //SELECT * FROM ROOT.BORROW
            List<Borrow> res = new ArrayList<Borrow>();
            while (rs.next()) {
                Borrow ac = new Borrow();
                ac.setCopy_book(rs.getInt("copy_book"));
                ac.setBookName(rs.getString("book_name"));
                ac.setStudent(rs.getLong("student"));
                java.sql.Date sqld = rs.getDate("Borrow_date");
                java.util.Date jud = new java.util.Date(sqld.getTime());
                java.sql.Date sqld1 = rs.getDate("until_date");
                java.util.Date jud1 = new java.util.Date(sqld1.getTime());
                ac.setBorrow_date(jud);
                ac.setUntil_date(jud1);
                res.add(ac);
            }
            cn.close();
            return res;
        } catch (Throwable ex) {
            Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void addBorrow(int copy_book, String book_name, long idStudent, Date Borrow_date, Date until_date) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();

            Calendar cal = Calendar.getInstance();
            cal.setTime(Borrow_date);
            cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + 3));
            until_date = cal.getTime();

//           String dateStr = "" + (gameDate.getYear() + 1900) +"-" + gameDate.getMonth() + 1 +"-" + gameDate.getDay();
            String sql = "INSERT INTO ROOT.\"Borrow\" (\"copy_book\", \"book_name\", \"student\", \"Borrow_date\", \"until_date\")"
                    + "VALUES (" + copy_book + ", '" + book_name + "', " + idStudent + ", '" + new java.sql.Date(Borrow_date.getTime()) + "', '" + new java.sql.Date(until_date.getTime()) + "')";

            String sql1 = "INSERT INTO ROOT.\"Book\" (\"isbnbook\", \"IDstudent\", \"code\", \"status\")"
                    + "VALUES (" + copy_book + ", " + idStudent + ", " + new Random().nextInt(99999999) + ", " + true + ")";

            int rowsEffected = st.executeUpdate(sql);

            int rowsEffected1 = st.executeUpdate(sql1);
            cn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from ROOT.\"BOOK\"");

            List<Book> res = new ArrayList<Book>();
            while (rs.next()) {

                Book ac = new Book();
                ac.setIsbn(rs.getInt("isbn"));
                ac.setBookName(rs.getString("bookName"));
                ac.setAuthorName(rs.getString("authorName"));
                ac.setCategory(rs.getString("category"));
                ac.setReleaseDate(rs.getInt("releaseDate"));
                ac.setCopiesInUse(rs.getInt("copiesInUse"));
                ac.setCopies(rs.getInt("copies"));
                res.add(ac);
            }

            cn.close();
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public Book getBook(int bookID) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from ROOT.\"BOOK\"");

            Book res = new Book();
            while (rs.next()) {
                if (rs.getInt("isbn") == bookID) {
                    Student ac = new Student();
                    res.setIsbn(rs.getInt("isbn"));
                    res.setBookName(rs.getString("bookName"));
                    res.setAuthorName(rs.getString("authorName"));
                    res.setCategory(rs.getString("category"));
                    res.setReleaseDate(rs.getInt("releaseDate"));
                    res.setCopiesInUse(rs.getInt("copiesInUse"));
                    res.setCopies(rs.getInt("copies"));
                }
            }

            cn.close();
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public void updatBook(int isbn, int num) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();

//           String dateStr = "" + (gameDate.getYear() + 1900) +"-" + gameDate.getMonth() + 1 +"-" + gameDate.getDay();
            String sql = "UPDATE ROOT.\"BOOK\" SET \"copiesInUse\" = \"copiesInUse\" + " + num + ","
                    + " \"copies\" = \"copies\" + " + num + "  WHERE \"isbn\" =" + isbn;

            int rowsEffected = st.executeUpdate(sql);
            cn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void DeleteBorrow(long id, int isbn) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();

            String sql = "Delete from ROOT.\"BORROW\" where \"STUDENT\" = "
                    + id + " AND \"copy_book\" = " + isbn;

            int rowsEffected = st.executeUpdate(sql);
            cn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void DeleteCopyBook(long id, int isbn) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();

            String sql = "Delete from ROOT.\"Book\" where \"IDstudent\" = "
                    + id + " AND \"isbnbook\" = " + isbn;

            int rowsEffected = st.executeUpdate(sql);
            cn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void updatStudentFine(int new_fine) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/LibraryDB";
            Connection cn = DriverManager.getConnection(urlCn, "root", "root");
            Statement st = cn.createStatement();

            String sql = "UPDATE ROOT.\"STUDENT\" SET \"fine\" = " + new_fine;

            int rowsEffected = st.executeUpdate(sql);
            cn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
