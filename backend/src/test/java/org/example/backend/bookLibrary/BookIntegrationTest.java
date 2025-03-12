package org.example.backend.bookLibrary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository bookRepository;

    @DirtiesContext
    @Test
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
    void deleteBook_shouldReturnEmptyBody() throws Exception {
        bookRepository.save(new Book("1", "Buch 1", "Author 1"));

        mvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }
}

