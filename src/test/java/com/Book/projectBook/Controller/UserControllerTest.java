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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController underTest;

    @Mock
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCapture;


//    @Test
//    public void createUserWhenMethodsPost() {
//
//        //given
//        User user = new User(
//                1L,
//                "FEDERICO",
//                "Ueno",
//                "federico@gmail.com",
//                21212121);
//
//        //when
//        String jsonBody = "{\"id\": 1,\"name\": \"Federico\", \"lastname\": \"Ueno\", \"email\": \"federico@gmail.com\", \"documentNumber\": \"21212121\"}";
//
//        mvc.perform(post("/user/createUser")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonBody)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//
//        //then
//        ArgumentCaptor<User> userArgumentCaptor =
//                ArgumentCaptor.forClass(User.class);
//        verify(userService).createUser(userArgumentCaptor.capture());
//
//        User captureUser = userArgumentCaptor.getValue();
//
//        assertThat(captureUser.getName()).isEqualTo(user.getName());
//    }

    @Test
    public void createUserWhenMethodsPost() throws Exception{

        User userMock = mock(User.class);

        //given
        User user = new User(
                1L,
                "FEDERICO",
                "Ueno",
                "federico@gmail.com",
                21212121);

//        doNothing().when(userService).createUser(any());

        when(userService.createUser(any())).thenReturn(userMock);

        ResponseEntity<User> responseEntity = underTest.createUser(user);

        verify(userService).createUser(userArgumentCapture.capture());

        assertEquals(user, userArgumentCapture.getValue());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());//que valor espero, cual recibo

    }

    @Test
    public void updateUserByIdWhenMethodsPut() {

        User userMock = mock(User.class);

        //given
        User user = new User(
                1L,
                "FEDERICO",
                "Ueno",
                "federico@gmail.com",
                21212121);

        when(userService.updateUser(any())).thenReturn(userMock);

        User userActual = underTest.updateUser(user);

        verify(userService).updateUser(userArgumentCapture.capture());
        assertEquals(user, userArgumentCapture.getValue());

        assertNotNull(userActual);
//        assertEquals(user, userActual);//que valor espero, cual recibo

    }
}