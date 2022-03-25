import axios from "axios";//tạo instance
export default axios.create({
    baseURL: "http://localhost:8085/api/students",
    headers: {
        "Content-type": "application/json"
    }
});