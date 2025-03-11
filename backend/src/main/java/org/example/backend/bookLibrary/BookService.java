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
}
