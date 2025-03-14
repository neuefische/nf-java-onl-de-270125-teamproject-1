import axios from "axios";
import {FormEvent, useEffect, useState} from "react";
import Book from "../components/Book.tsx";
import {Book as BookType} from "../types/Book.ts"

export default function BooksPage() {
    const [books, setBooks] = useState<BookType[]>([]);
    const [newBook, setNewBook] = useState<BookType>({id: "", title: "", author: ""})

    useEffect(() => {
        fetchBooks();
    }, []);

    const fetchBooks = () => {
        axios.get("/api/books")
            .then(response => setBooks(response.data))
            .catch(error => console.error("Error fetching books: ", error))
    };

    const addBook = (e: FormEvent<HTMLFormElement>) =>{
        e.preventDefault()
        axios.post("/api/books", newBook)
            .then(() => {
                setBooks([...books, newBook])
            })
            .catch(error => {
                console.error("Adding book failed", error)
            })
    }

    const deleteBook = (id:string) => {
        axios.delete("/api/books/" + id)
            .then(() => {
                setBooks((books) => books.filter((book)=> book.id !== id))
            })
    }

    return (
        <div>
            {
                books.map((book) => (
                    <Book key={book.id} book={book} deleteBook={deleteBook}/>
                ))
            }
            <form onSubmit={addBook}>
                <input
                    placeholder="title"
                    value={newBook.title}
                    onChange={(event) => setNewBook({ ...newBook, title: event.target.value })}
                />
                <input
                    placeholder="author"
                    value={newBook.author}
                    onChange={(event) => setNewBook({ ...newBook, author: event.target.value })}
                />
                <button>Add book</button>
            </form>
        </div>
    );
}