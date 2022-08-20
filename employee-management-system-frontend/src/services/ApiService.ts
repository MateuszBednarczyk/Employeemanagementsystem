import LoginRequestDto from "@/models/login-request-dto";
import RegisterRequestDto from "@/models/register-request-dto";
import axios from "axios";
import UserAccountService from "./UserAccountService";

const baseUrl = "http://localhost:8080/api";

const axiosWithTokenCheck =  axios.create();

export const httpInterceptor = axiosWithTokenCheck.interceptors.request.use(
  async (request) => {
    console.log('url: ' + request.baseURL);
    if (
      UserAccountService.IsJwtExpired() &&
      UserAccountService.IsLogged() &&
      UserAccountService.GetRefreshToken() != null &&
      request.baseURL
    ) {
      console.log("token is expired, logged out");
      UserAccountService.Logout()
    }
    return request;
  },
  (error: any) => {
    return Promise.reject(error);
  }
);

const ApiService = {
  //TODO: delete later
  async AddTestDepartment(departmentName: string) {
    const body = { departmentName: departmentName };
    return await axiosWithTokenCheck.post(`${baseUrl}/department/add`, body);
  },

  async SendRegisterRequest(request: RegisterRequestDto) {
    return await axios.post(`${baseUrl}/users/register`, request);
  },

  async SendLoginRequest(request: LoginRequestDto) {
    // return await axios.post(`${baseUrl}/users/login`, request);
    return await axios({
      method: "post",
      url: `${baseUrl}/users/login`,
      headers: {
        "Content-Type": "application/json",
      },
      data: request,
    });
  },

  async getDepartments() {
    return await axiosWithTokenCheck.get(`${baseUrl}/department/`, {
      headers: {
        Authorization: "Bearer " + UserAccountService.GetJwt(),
      },
    });
  },
  async refreshToken() {
    // console.log('a')
    const instance = axios.create()
    const res = await instance.get(`${baseUrl}/users/token/refresh`, {
      headers: {
        Authorization: "Bearer " + UserAccountService.GetRefreshToken()!,
      },
    });
    // console.log("token: " + res);
  },
};
export default ApiService;
