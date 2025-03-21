import {NavLink} from "react-router-dom";
import "../index.css"

export default function Header({isLoggedIn}: {isLoggedIn: boolean | undefined}) {

    return (
        <div className={"header-container"}>
            <h1>Teamprojekt 1: Book Library</h1>
            <div className={"navbar-container"}>
            <NavLink to={"/"} className={"nav-item"}>Home</NavLink>
                {isLoggedIn && <NavLink to={"/books"} className={"nav-item"}>View all books</NavLink>}
            </div>
        </div>
    );
}