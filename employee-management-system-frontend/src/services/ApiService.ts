import LoginRequestDto from "@/models/login-request-dto";
import RegisterRequestDto from "@/models/register-request-dto";
import axios from "axios";
import UserAccountService from "./UserAccountService";

const baseUrl = "http://localhost:8080/api";
const ApiService = {
  //TODO: delete later
  async AddTestDepartment(departmentName:string) {
    const body = {departmentName:departmentName}
    return await axios.post(`${baseUrl}/department/add`, body);
  },

  async SendRegisterRequest(request: RegisterRequestDto) {
    return await axios.post(`${baseUrl}/users/register`, request);
  },

  async SendLoginRequest(request: LoginRequestDto) {
    // return await axios.post(`${baseUrl}/users/login`, request);
    return await axios({
      method:'post',
      url:`${baseUrl}/users/login`,
      headers:{
        "Content-Type": "application/json"
      },
      data:request
    })
  },

  async getDepartments(){
    return await axios.get(`${baseUrl}/department/`, {
      headers:{
        Authorization: "Bearer " + UserAccountService.GetJwt()
      }
    });
  }
};
export default ApiService;
