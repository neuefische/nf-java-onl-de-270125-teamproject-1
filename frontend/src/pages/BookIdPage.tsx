import {Link, useParams} from "react-router-dom";
import axios from "axios";
import {useEffect, useState} from "react";
import {Book} from "../types/Book.ts";

export default function BookIdPage() {
    const params = useParams();
    const bookId = params.id;

    const [book, setBook] = useState<Book>({id: "", title: "", author: ""});

    const fetchBook = () => {
        axios.get(`/api/books/${bookId}`)
            .then(response => setBook(response.data))
            .catch(error => console.error("Error fetching book: ", error));
    };

    useEffect(() => {
        fetchBook();
    }, []);

    return (
        <div>
            <p>Id: {book.id}</p>
            <p>Title: {book.title}</p>
            <p>Author: {book.author}</p>
            <Link to="/books">Back to books</Link>
        </div>
    );
}