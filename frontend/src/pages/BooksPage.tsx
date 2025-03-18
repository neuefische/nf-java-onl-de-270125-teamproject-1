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

    const addBook = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        axios.post("/api/books", newBook)
            .then(() => {
                setBooks([...books, newBook])
            })
            .catch(error => {
                console.error("Adding book failed", error)
            })
    }

    const deleteBook = (id: string) => {
        axios.delete("/api/books/" + id)
            .then(() => {
                setBooks((books) => books.filter((book) => book.id !== id))
            })
    }

    return (
        <div className={"main-container"}>
        <div className={"book-container"}>
            {
                books.map((book) => (
                    <Book key={book.id} book={book} deleteBook={deleteBook}/>
                ))
            }
        </div>


            <div className={"add-book-container"}>
                <form onSubmit={addBook}>
                    <div>
                    <input
                        placeholder="title"
                        value={newBook.title}
                        onChange={(event) => setNewBook({...newBook, title: event.target.value})}
                    />
                    <input
                        placeholder="author"
                        value={newBook.author}
                        onChange={(event) => setNewBook({...newBook, author: event.target.value})}
                    />
                    </div>
                    <button className={"add-book-button"}>Add</button>
                </form>
            </div>
        </div>
    );
}