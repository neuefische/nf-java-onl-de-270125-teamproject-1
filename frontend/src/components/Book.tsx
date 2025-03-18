import {Book as BookType} from "../types/Book.ts";
import {Link} from "react-router-dom";

type BookProps = {
    book: BookType;
    deleteBook: (id: string) => void
}

export default function Book({book, deleteBook}: BookProps) {
    return (
        <div className={"book-item"}>
            <div className={"book-info"}>
                <p>Title: {book.title}</p>
                <p>Author: {book.author}</p>
            </div>
            <div className={"book-utility"}>
                <button className={"delete-book-button"} onClick={() => {deleteBook(book.id)}}>delete</button>
                <Link to={`/books/${book.id}`}>Details</Link>
            </div>
        </div>
    );
}