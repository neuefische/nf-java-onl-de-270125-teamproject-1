package org.example.backend.bookLibrary;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    List<Book> getAll(){
        return bookService.getAll();
    }

    @PostMapping
    Book addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @DeleteMapping("{id}")
    void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }

}
