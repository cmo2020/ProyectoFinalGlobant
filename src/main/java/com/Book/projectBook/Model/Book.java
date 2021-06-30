package com.Book.projectBook.Model;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", length = 45)
    @NotEmpty(message = "Title is mandatory")
    private  String title;

    @Column(name="author", length = 45)
    @NotEmpty(message = "Author is mandatory")
    private  String author;

    @Column
    @NotNull(message = "Date is mandatory")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date publishedDate;

    @OneToOne(mappedBy = "book") //propiedad dentro de booking
    private Booking booking;

    public Book() {
    }

    public Book(Long id, String title, String author, @NonNull Date publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = (title != null) ? title.toUpperCase() : null;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author =  (author != null) ? author.toUpperCase() : null;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getStatus() {
        if (booking == null) {
            return "available";
        } else {
            return "reserved";
        }
    }

   public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
