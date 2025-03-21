import {Navigate, Outlet} from "react-router-dom";

export default function ProtectedRoutes({isLoggedIn}: {isLoggedIn: boolean | undefined}) {
    if (isLoggedIn === undefined) {
        return <p>Loading...</p>
    }

    return isLoggedIn ? <Outlet/> : <Navigate to="/"/>;
}