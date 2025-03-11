import {Route, Routes} from "react-router-dom";
import BooksPage from "./pages/BooksPage.tsx";

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<p>wefwefwef</p>}/>
            <Route path="/books" element={<BooksPage/>}/>
        </Routes>
    );
}