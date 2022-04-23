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
import java.text.SimpleDateFormat;
public class Book  implements Serializable{
    private String bookName;
    private int isbn;
    private String authorName;
    private String category;
    private int releaseDate;
    private int copiesInUse = 0;
    private int copies;

    
    public Book(){
        
    }
    
    
    public Book(String bookName, int isbn, String authorName, String category, int releaseDate, int copies) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.authorName = authorName;
        this.category = category;
        this.releaseDate = releaseDate;
        this.copies = copies;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getCopiesInUse() {
        return copiesInUse;
    }

    public void setCopiesInUse(int copiesInUse) {
        this.copiesInUse = copiesInUse;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category=category;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int release_Date) {
        this.releaseDate = release_Date;
    }

    
}
