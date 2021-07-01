package com.Book.projectBook.Controller;

import com.Book.projectBook.Model.Booking;
import com.Book.projectBook.Model.User;
import com.Book.projectBook.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController underTest;

    @Mock
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCapture;

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

    @Test
    public void deleteUserByIdWhenMethodsDelete() {

        String result = underTest.deleteUserById(1L);

        verify(userService, times(1)).deleteUserById(1L);

        assertThat(result).isEqualTo(null);

    }

    @Test
    void testGetBookingById() {

        User user = new User (
                1L,
                "FEDERICO",
                "Ueno",
                "federico@gmail.com",
                21212121);

        when(userService.getUserById(user)).thenReturn(Optional.of(user));

        Optional<User> result = underTest.getUserById(user);

        verify(userService, times(1)).getUserById(user);

        assertThat(result).isEqualTo(Optional.of(user));

    }


}