package org.example.backend.bookLibrary;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
        Book book1 = Book.builder()
                .id("1")
                .author("Test")
                .title("Test")
                .build();

        doNothing().when(mockBookRepository).deleteById(book1.id());
        when(mockBookRepository.findById(book1.id())).thenReturn(Optional.of(book1));

        // When
        bookService.getBookById(book1.id());
        bookService.deleteBook(book1.id());

        // Then
        verify(mockBookRepository).deleteById(book1.id());
    }

    @Test
    void getBookById() {
        //GIVEN
        Book book1 = Book.builder()
                .id("1")
                .author("Test")
                .title("Test")
                .build();

        when(mockBookRepository.findById(book1.id())).thenReturn(Optional.of(book1));
        //WHEN
        Book actual = bookService.getBookById("1");
        //THEN
        verify(mockBookRepository).findById(book1.id());
        assertEquals(book1, actual);
    }

    @Test
    void updateBookById() {
        // GIVEN
        Book book = new Book("1", "Test", "Author");
        BookDTO bookDTO = new BookDTO("Test2", "Author2");

        when(mockBookRepository.findById(book.id())).thenReturn(Optional.of(book));

        Book updatedBook = new Book(book.id(), bookDTO.title(), bookDTO.author());
        when(mockBookRepository.save(any(Book.class))).thenReturn(updatedBook);

        // WHEN
        Book actual = bookService.updateBookById(bookDTO, book.id());

        // THEN
        verify(mockBookRepository).findById(book.id());
        verify(mockBookRepository).save(any(Book.class));
        assertEquals(updatedBook, actual);
    }
}