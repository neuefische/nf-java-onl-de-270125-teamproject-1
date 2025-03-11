import axios from "axios";
import {useEffect, useState} from "react";
import Book from "../components/Book.tsx";

export default function BooksPage() {
    const [books, setBooks] = useState([]);

    const fetchBooks = () => {
        axios.get("/api/books")
            .then(response => setBooks(response.data))
            .catch(error => console.error("Error fetching books: ", error))
    };

    useEffect(() => {
        fetchBooks();
    }, []);

    return (
        <div>
            {
                books.map((book) => (
                    <Book book={book} />
                ))
            }
        </div>
    );
}