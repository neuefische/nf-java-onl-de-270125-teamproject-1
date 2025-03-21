import {Route, Routes} from "react-router-dom";
import BooksPage from "./pages/BooksPage.tsx";
import BookIdPage from "./pages/BookIdPage.tsx";
import Header from "./components/Header.tsx";
import Home from "./pages/Home.tsx";
import {useEffect, useState} from "react";
import axios from "axios";
import ProtectedRoutes from "./ProtectedRoutes.tsx";

export default function App() {
    const [isLoggedIn, setIsLoggedIn] = useState<boolean | undefined>(undefined);

    const loadUser = () => {
        axios.get('/api/auth/me')
            .then(() => {
                setIsLoggedIn(true);
            })
            .catch(error => {
                console.error("Fehler beim Laden des Benutzers:", error);
                setIsLoggedIn(false);
            });
    }

    useEffect(loadUser, []);

    return (
        <>
        <Header isLoggedIn={isLoggedIn} />
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route element={<ProtectedRoutes isLoggedIn={isLoggedIn} />}>
                <Route path="/books" element={<BooksPage/>}/>
            </Route>
            <Route path="/books/:id" element={<BookIdPage/>}/>
        </Routes>
        </>
    );
}