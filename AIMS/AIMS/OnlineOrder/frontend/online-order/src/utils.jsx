import { toast, ToastContainer } from "react-toastify";
// import "react-toastify/dist/ReactToastify.css";
import 'react-toastify/dist/ReactToastify.css'
class ToastUtil {
  static showToastSuccess(message) {
    toast.success(message, {
      position: "top-right",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      progress: undefined,
      theme: "light",
      });
  }

  static showToastError(message) {
    toast.error(message, {
      position: "top-right",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      progress: undefined,
      theme: "light",
      });
  }

  static showToastWarning(message) {
    toast.warn(message, {
      position: "top-right",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      progress: undefined,
      theme: "light",
      });
  }

 
}

export default ToastUtil;
