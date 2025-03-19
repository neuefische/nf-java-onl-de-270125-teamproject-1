import {Route, Routes} from "react-router-dom";
import BooksPage from "./pages/BooksPage.tsx";
import BookIdPage from "./pages/BookIdPage.tsx";
import Header from "./components/Header.tsx";
import Home from "./pages/Home.tsx";

export default function App() {
    return (
        <>
        <Header/>
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/books" element={<BooksPage/>}/>
            <Route path="/books/:id" element={<BookIdPage/>}/>
        </Routes>
        </>
    );
}