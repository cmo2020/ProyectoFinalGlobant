package com.Book.projectBook.Service;


import com.Book.projectBook.Model.User;
import java.util.Optional;

public interface UserServiceInterface {

     User createUser(User user);

     User updateUser(User user);

     String deleteUserById(Long idUser);

     Optional<User> getUserById(User user);
}
