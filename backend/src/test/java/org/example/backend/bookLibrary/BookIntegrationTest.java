package org.example.backend.bookLibrary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;

@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository bookRepository;

    @DirtiesContext
    @Test
    @WithMockUser
    void getAllBooks_shouldReturnListWithOneObject_whenOneObjectWasSavedInRepository() throws Exception {
        Book book = new Book("1", "Buch 1", "Author 1");
        bookRepository.save(book);

        mvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                 {
                                     "id": "1",
                                     "title": "Buch 1",
                                     "author": "Author 1"
                                 }
                                ]
                                """
                ));
    }

    @DirtiesContext
    @Test
    @WithMockUser
    void addBook_shouldReturnCreatedBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                         {
                                              "title": "Buch 1",
                                              "author": "Author 1"
                                         }
                                        """
                        ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                 {
                                     "title": "Buch 1",
                                     "author": "Author 1"
                                 }
                                """
                ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @DirtiesContext
    @Test
    @WithMockUser
    void deleteBook_shouldReturnEmptyBody() throws Exception {
        bookRepository.save(new Book("1", "Buch 1", "Author 1"));

        mvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void getBookById() throws Exception {
        Book bookToSave = Book.builder()
                .id("1")
                .title("Test")
                .author("Test")
                .build();
        bookRepository.save(bookToSave);

        mvc.perform(MockMvcRequestBuilders.get("/api/books/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    id: "1",
                                    title: "Test",
                                    author: "Test"
                                }
                                """
                ));

    }

    @DirtiesContext
    @Test
    @WithMockUser
    void updateBookById_shouldReturnUpdatedBook() throws Exception {
        bookRepository.save(new Book("1", "Buch 1", "Author 1"));

        mvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                         {
                                              "title": "Buch 2",
                                              "author": "Author 2"
                                         }
                                        """
                        ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                 {
                                     "id": "1",
                                     "title": "Buch 2",
                                     "author": "Author 2"
                                 }
                                """
                ));
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void globalExceptionHandling_shouldReturnErrorMessage() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/books/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "message": "No Book found with id: 1"
                                }
                                """
                ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists());

    }

    @Test
    @DirtiesContext
    void getMe() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/auth/me").with(oidcLogin().userInfoToken(token -> token
                                .claim("login", "test-user")
                        ))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("test-user"));
    }
}

