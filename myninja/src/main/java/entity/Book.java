package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;

    @NotNull
    @Column(name = "name")
    String bookName;

    @NotNull
    @Column(name = "author")
    String bookAuthor;

    @NotNull
    @Column(name = "availability")
    String bookAvail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAvail() {
        return bookAvail;
    }

    public void setBookAvail(String bookAvail) {
        this.bookAvail = bookAvail;
    }

}
