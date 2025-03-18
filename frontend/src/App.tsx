import {Route, Routes} from "react-router-dom";
import BooksPage from "./pages/BooksPage.tsx";
import BookIdPage from "./pages/BookIdPage.tsx";
import LoginPage from "./pages/LoginPage.tsx";

export default function App() {
    return (
        <>
            <LoginPage/>
            <Routes>
                <Route path="/" element={<h1>Home</h1>}/>
                <Route path="/books" element={<BooksPage/>}/>
                <Route path="/books/add" element={<BooksPage/>}/> {/*add component*/}
                <Route path="/books/:id" element={<BookIdPage/>}/>
            </Routes>
        </>

    );
}