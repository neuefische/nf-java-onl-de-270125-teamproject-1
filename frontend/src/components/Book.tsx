import { Book as BookType } from "../types/Book.ts";
import {Link} from "react-router-dom";

type BookProps = {
    book: BookType;
    deleteBook: (id:string) => void
}

export default function Book({ book, deleteBook}: BookProps) {
    return (
        <div>
            <p>Title: {book.title}</p>
            <p>Author: {book.author}</p>

            <button onClick={()=>{deleteBook(book.id)}}>x</button>
            <Link to={`/books/${book.id}`}>Details</Link>
        </div>
    );
}