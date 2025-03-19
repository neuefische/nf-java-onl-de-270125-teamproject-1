import axios from "axios";
import {useEffect} from "react";

LoginPage.propTypes = {

};

export default function LoginPage() {
    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080': window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    const loadUser = () => {
        axios.get('/api/auth/me')
            .then(response => {
                console.log(response)
            })
    }

    useEffect(
        loadUser
    , [])

    return (
        <div>
            <button onClick={login}>Login</button>
        </div>
    );
}
