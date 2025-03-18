import {Link, useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import {useEffect, useState} from "react";
import {Book} from "../types/Book.ts";

export default function BookIdPage() {
    const params = useParams();
    const bookId = params.id;
    const navigate = useNavigate();

    const [book, setBook] = useState<Book>({id: "", title: "", author: ""});


    const fetchBook = () => {
        axios.get(`/api/books/${bookId}`)
            .then(response => setBook(response.data))
            .catch(error => console.error("Error fetching book: ", error));
    };

    const updateBook = () => {
        axios.put(`/api/books/${bookId}`, book)
            .then(() => {
                console.log(book)
            })
        navigate("/books")
    };

    useEffect(() => {
        fetchBook();
    }, []);

    return (
        <div className={"view-details-container"}>
            <form onSubmit={updateBook}>
                <label>Id</label>
                <input type={"text"} value={book.id} disabled={true}/>

                <label>Title</label>
                <input type={"text"} value={book.title}
                       onChange={(event) => setBook({...book, title: event.target.value})}/>

                <label>Author</label>
                <input type={"text"} value={book.author}
                       onChange={(event) => setBook({...book, author: event.target.value})}/>
                <button>Update Book</button>
            </form>
            <Link to="/books">Back to books</Link>
        </div>
    );
}