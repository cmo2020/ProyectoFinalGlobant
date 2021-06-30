package com.Book.projectBook.Repository;



import com.Book.projectBook.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {


}
