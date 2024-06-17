import Order from "./Order.jsx";
import {ToastContainer} from "react-toastify";
import {createBrowserRouter, RouterProvider} from "react-router-dom";

const router = createBrowserRouter([
    {
        path: "/order/:id",
        element: <Order />
    }
])

function App() {
    return (
        <>
            <RouterProvider router={router}>
            </RouterProvider>
            <ToastContainer />
        </>
    )
}
export default App;