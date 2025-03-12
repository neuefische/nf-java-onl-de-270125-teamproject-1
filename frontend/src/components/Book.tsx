import { Book as BookType } from "../types/Book.ts";

type BookProps = {
    book: BookType;
}

export default function Book({ book }: BookProps) {
    return (
        <div>
            <p>Title: {book.title}</p>
            <p>Author: {book.author}</p>
        </div>
    );
}