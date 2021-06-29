package com.Book.projectBook.Model;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import java.util.Set;


@Entity
@Table(name="user")
public class User {

    @Id
//    @Column(name="idUser")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", length = 45)
    @NotEmpty
    private String name;
    @Column(name="lastname", length = 45)
    @NotEmpty
    private String lastname;

    @Column(name="email", length = 50,unique = true)
    @NotEmpty(message = "Email is mandatory") @Pattern(regexp =  "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid email format")
    private String email;

    @Column(name="documentNumber",nullable = false)
    private int documentNumber;


    public User() {
    }

    public User(Long idUser, String name, String lastname, String email, int documentNumber) {
        this.id = idUser;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.documentNumber = documentNumber;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name != null) ? name.toUpperCase() : null;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = (lastname != null) ? lastname.toUpperCase() : null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email != null) ? email.toUpperCase() : null;

    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }
}

