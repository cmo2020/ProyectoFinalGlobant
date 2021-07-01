package com.Book.projectBook.Controller;

import com.Book.projectBook.Model.Book;
import com.Book.projectBook.Model.User;
import com.Book.projectBook.Service.BookService;
import com.Book.projectBook.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.DataInput;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    @Disabled
    void listBook()  throws Exception {
//        String uri = "/book/listBook";
//        MvcResult mvcResult = (MvcResult) mvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$[0].title",is("Harry Potter")))
//                        .andExpect((ResultMatcher) jsonPath("$[1].title", is("Yoda")));
//
//             ObjectMapper mapper = new ObjectMapper();
//
//              List<Book> actual = mapper.readValue(mvcResult.getResponse().getContentAsString(),
//                new TypeReference<List<Book>>() {});

    }

    @Test
    @Disabled
    void listAvailable() {
    }

    @Test
    @Disabled
    void listReserved() {
    }

    @Test

    void createBook() throws Exception {
        Date publishedDate = new Date(2000, 1, 1);
        Book book = new Book(1L, "HARRY POTTER", "ANON", new Date());
        String jsonBody = "{\"id\": 1,\"title\": \"Harry Potter\", \"author\": \"ANon\", \"publishedDate\":  \"2000-01-01\"}";
        mvc.perform(post("/book/createBook").contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        ArgumentCaptor<Book> bookArgumentCaptor =  ArgumentCaptor.forClass(Book.class);

        verify(bookService).createBook(bookArgumentCaptor.capture());

        Book captureBook = bookArgumentCaptor.getValue();

        assertThat(captureBook.getTitle()).isEqualTo(book.getTitle());

    }

    @Test
    @Disabled
    void getBookById() throws Exception{


    }

    @Test
    @Disabled
    void updateBook() throws Exception{
        Date publishedDate = new Date(2000, 1, 1);
        Book book = new Book(1L, "HARRY POTTER", "ANON", new Date());
        String jsonBody = "{\"id\": 1,\"title\": \"Yoda\", \"author\": \"Somebody\", \"publishedDate\":  \"2010-01-01\"}";
        mvc.perform(put("/updateBookById/{bookId}").contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        ArgumentCaptor<Book> bookArgumentCaptor =  ArgumentCaptor.forClass(Book.class);

        verify(bookService).updateBook(bookArgumentCaptor.capture());

        Book captureBook = bookArgumentCaptor.getValue();

        assertThat(captureBook.getTitle()).isEqualTo(book.getTitle());
    }

    @Test
    @Disabled
    void deleteBookById() {
    }
}