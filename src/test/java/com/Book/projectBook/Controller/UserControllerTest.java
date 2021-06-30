package com.Book.projectBook.Controller;

import com.Book.projectBook.Model.User;
import com.Book.projectBook.Repository.UserRepository;
import com.Book.projectBook.Service.UserService;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
//@ComponentScan(basePackages = {"com.mypackage"})
//@SpringBootTest
//@AutoConfigureMockMvc
class UserControllerTest {

    @InjectMocks
    private UserController underTest;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;


    @Test
    public void createUserWhenMethodsPost() throws Exception {

        //given
        User user = new User(
                1L,
                "FEDERICO",
                "Ueno",
                "federico@gmail.com",
                21212121);

        //when
        String jsonBody = "{\"id\": 1,\"name\": \"Federico\", \"lastname\": \"Ueno\", \"email\": \"federico@gmail.com\", \"documentNumber\": \"21212121\"}";

        mvc.perform(post("/user/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userService).createUser(userArgumentCaptor.capture());

        User captureUser = userArgumentCaptor.getValue();

        assertThat(captureUser.getName()).isEqualTo(user.getName());
    }

//    @Test
//    public void updateUserWhenMethodsPut() {
//
//        User userTest = new User(1l,"Federico","Ueno","federico@gmail.com",21212121);
//
////        given(userController.updateUser(userTest)).willReturn(userTest);
////
//////        given(userRepository.findById(userTest.getId())).willReturn(Optional.of(userTest));
////
////        mvc.perform(put("/updateUser"));
//
//        when(userRepository.findById(userTest.getId())).thenReturn(Optional.of(userTest));
//        userController.updateUser(userTest);
//        verify(userController).updateUser(userTest);
//
//    }
}