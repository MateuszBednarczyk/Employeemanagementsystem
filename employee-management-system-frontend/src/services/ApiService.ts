import LoginRequest from "@/models/requests/login-request";
import RegisterRequest from "@/models/requests/register-request";
import axios from "axios";
import UserAccountService from "./UserAccountService";

const baseUrl = "http://localhost:8080/api";

const axiosWithTokenCheck = axios.create();

export const httpInterceptor = axiosWithTokenCheck.interceptors.request.use(
  async (request) => {
    // console.log('url: ' + request.baseURL);
    if (
      UserAccountService.IsJwtExpired() &&
      UserAccountService.IsLogged() &&
      UserAccountService.GetRefreshToken() != null &&
      request.baseURL
    ) {
      console.log("token has expired, logged out");
      UserAccountService.Logout();
    }
    request.headers = {Authorization:"Bearer " + UserAccountService.GetJwt()}
    return request
  },
  (error: any) => {
    return Promise.reject(error);
  }
);

const ApiService = {
  //TODO: delete later
  async AddDepartment(departmentName: string) {
    const body = { departmentName: departmentName };
    return await axiosWithTokenCheck.post(`${baseUrl}/department/add`, body);
  },

  async SendRegisterRequest(request: RegisterRequest) {
    return await axios.post(`${baseUrl}/users/register`, request);
  },

  async SendLoginRequest(request: LoginRequest) {
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
    return await axiosWithTokenCheck.get(`${baseUrl}/department/`);
  },
  async DeleteDepartment(departmentName: string) {
    return await axiosWithTokenCheck.delete(
      `${baseUrl}/department/delete/${departmentName}`
    );
  },
  async refreshToken() {
    // console.log('a')
    const instance = axios.create();
    const res = await instance.get(`${baseUrl}/users/token/refresh`, {
      headers: {
        Authorization: "Bearer " + UserAccountService.GetRefreshToken()!,
      },
    });
    // console.log("token: " + res);
  },
};
export default ApiService;
