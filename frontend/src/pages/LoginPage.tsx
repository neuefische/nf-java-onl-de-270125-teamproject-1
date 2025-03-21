import axios from "axios";
import {useEffect, useState} from "react";

export default function LoginPage() {
    const [username, setUsername] = useState("");

    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080': window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    function logout() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin

        window.open(host + '/logout', '_self')
    }

    const loadUser = () => {
        axios.get('/api/auth/me')
            .then(response => {
                console.log(response)
                setUsername(response.data);
            })
            .catch(error => {
                console.error("Fehler beim Laden des Benutzers:", error);
            });
    }

    useEffect(
        loadUser
    , [])

    return (
        <div>
            {username ? <button onClick={logout}>Logout</button> : <button onClick={login}>Login</button>}
            {username && <p>{username}</p>}
        </div>
    );
}
