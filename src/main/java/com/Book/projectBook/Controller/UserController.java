package com.Book.projectBook.Controller;

import com.Book.projectBook.Model.Book;
import com.Book.projectBook.Model.User;
import com.Book.projectBook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @RequestMapping("/getUserById/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") User user) {
        return userService.getUserById(user);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public String deleteUserById(@PathVariable("userId") Long idUser) {
        return userService.deleteUserById(idUser);
    }

    @PutMapping("/updateUserById/{userId}")
    public User updateUser(@RequestBody User user) {

        return userService.updateUser(user);
    }
}