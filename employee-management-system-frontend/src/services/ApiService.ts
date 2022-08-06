import RegisterRequestDto from "@/models/register-request-dto";
import axios from "axios";

const baseUrl = "http://localhost:8080/api";
const ApiService = {
  async SendRegisterRequest(request: RegisterRequestDto) {
    return await axios.post(`${baseUrl}/users/register`, {
        body: request
    });
  },
};
export default ApiService;
