package org.example.backend.bookLibrary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book addBook(BookDTO bookDTO) {
        Book newBook = new Book(null, bookDTO.title(), bookDTO.author());
        return bookRepository.save(newBook);
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
