/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author rafeqfiad
 */
import java.io.Serializable;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
public class Borrow implements Serializable{
    
    private int copy_book;
    private String book_name;
    private long student;
    private Date Borrow_date;
    private Date until_date;

    public Borrow() {

    }

    public Date getUntil_date() {
        return until_date;
    }

    public void setUntil_date(Date until_date) {
        this.until_date = until_date;
    }

    public Borrow(int copy_book, long student) {
        Borrow_date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(Borrow_date);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + 3));
        until_date = cal.getTime();
        this.copy_book = copy_book;
        this.student = student;

    }

    public int getCopy_book() {
        return copy_book;
    }

    public String getBookName() {
        return book_name;
    }

    public void setBookName(String book_name) {
        this.book_name = book_name;
    }

    public void setCopy_book(int copy_book) {
        this.copy_book = copy_book;
    }

    public long getStudent() {
        return student;
    }

    public void setStudent(long student) {
        this.student = student;
    }

    public Date getBorrow_date() {
        return Borrow_date;
    }

    public void setBorrow_date(Date Borrow_date) {
        this.Borrow_date = Borrow_date;
    }
}
