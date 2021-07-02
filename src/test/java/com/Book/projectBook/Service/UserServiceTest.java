package com.Book.projectBook.Service;

import com.Book.projectBook.Model.User;
import com.Book.projectBook.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository);
    }

    @Test
    public void canCreateUser() {
        //given
        User user = new User(
                1L,
                "Federico",
                "Ueno",
                "federico@gmail.com",
                21212121);

        //when
        underTest.createUser(user);

        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User captureUser = userArgumentCaptor.getValue();

        assertThat(captureUser).isEqualTo(user);
    }

    @Test
    void canUpdateUser() {

        //given
        User userMock = new User(
                1L,
                "Federico",
                "Ueno",
                "federico@gmail.com",
                21212121
        );

        given(userRepository.findById(anyLong())).willReturn(Optional.of(userMock));

        //when
        User userUpdate = new User(1L, "FedericoUpdate", "UenoUpdate", "federico@gmail.com", 21212121);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userUpdate));

        //then
        User userActual = underTest.updateUser(userUpdate);

        verify(userRepository).save(userActual);
        verify(userRepository, times(2)).findById(any());

        assertThat(userActual.getName()).isEqualTo("FEDERICOUPDATE");
    }

    @Test
    void canDeleteById() {

        String result = underTest.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);
        assertThat(result).isEqualTo("User removed \n" + "IdUser:" + 1L);
    }

    @Test
    void canGetBookById() {
        User user = new User(1L,"Federico","Ueno","federico@gmail.com", 21212121);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> bookObtained = underTest.getUserById(user);
        verify(userRepository, times(1)).findById(1L);
    }
}