package com.Book.projectBook.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;



@Entity
@Table(name="booking")
public class Booking {

    @Id
    @Column(name="idBooking")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long idBooking;


    @NotNull(message = "Date is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern="dd-MM-yyyy")
    private Date startDate;

    @NotNull(message = "Date is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern="dd-MM-yyyy")
    private Date endDate;


    @OneToOne
    private User user;

    @OneToOne
    @JoinColumn(name = "book_id")
    @JsonProperty( value = "book", access = JsonProperty.Access.WRITE_ONLY)
    private Book book;

    public Booking() {
    }

    public Booking(Long idBooking, @NonNull Date startDate, @NonNull Date endDater) {
        this.idBooking = idBooking;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Long idBooking) {
        this.idBooking = idBooking;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
