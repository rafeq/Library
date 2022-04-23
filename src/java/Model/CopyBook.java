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
import java.util.Random;
public class CopyBook  extends Book implements Serializable{
    
    private int isbnbook;
    private long IDstudent;
    private int code;
    private boolean status;
    
    public CopyBook(String bookName, int isbn, String authorName, String category, int releaseDate, int copies) {
        super(bookName, isbn, authorName, category, releaseDate, copies);
    }
    
    public int getBook() {
        return isbnbook;
    }

    public void setBook(int book) {
        this.isbnbook = book;
    }

    public long getStudent() {
        return IDstudent;
    }

    public void setStudent(long student) {
        this.IDstudent = student;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
