import {Route, Routes} from "react-router-dom";
import BooksPage from "./pages/BooksPage.tsx";

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<h1>Home</h1>}/>
            <Route path="/books" element={<BooksPage/>}/>
            <Route path="/books/add" element={<BooksPage/>}/> {/*add component*/}
        </Routes>
    );
}