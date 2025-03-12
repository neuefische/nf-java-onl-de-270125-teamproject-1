package org.example.backend.bookLibrary;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private final BookRepository mockBookRepository = mock(BookRepository.class);
    private final BookService bookService = new BookService(mockBookRepository);

    @Test
    public void getAllTest() {
        // If
        List<Book> expectedBooks = List.of(
                new Book("1", "Buch 1", "Author 1"),
                new Book("2", "Buch 2", "Author 2")
        );
        when(mockBookRepository.findAll()).thenReturn(expectedBooks);

        // When
        List<Book> result = bookService.getAll();

        // Then
        verify(mockBookRepository).findAll();
        assertEquals(expectedBooks, result);
    }

    @Test
    public void addBookTest() {
        // If
        Book expectedBook = new Book("1", "Buch 1", "Author 1");
        BookDTO bookDTO = new BookDTO("Buch 1", "Author 1");
        when(mockBookRepository.save(any(Book.class))).thenReturn(expectedBook);

        // When
        Book result = bookService.addBook(bookDTO);

        // Then
        verify(mockBookRepository).save(any(Book.class));
        assertEquals(expectedBook, result);
    }

    @Test
    public void deleteBookTest() {
        // If
        String bookId = "1";
        doNothing().when(mockBookRepository).deleteById(bookId);

        // When
        bookService.deleteBook(bookId);

        // Then
        verify(mockBookRepository).deleteById(bookId);
    }
}