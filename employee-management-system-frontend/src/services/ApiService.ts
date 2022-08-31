import { DeleteEmployeeRequest } from "@/models/requests/delete-employee-request";
import LoginRequest from "@/models/requests/login-request";
import RegisterRequest from "@/models/requests/register-request";
import axios from "axios";
import UserAccountService from "./UserAccountService";

const baseUrl = "http://localhost:8080/api";

const axiosWithTokenCheck = axios.create();

export const httpInterceptor = axiosWithTokenCheck.interceptors.request.use(
  async (request) => {
    // console.log('jwt: ');
    // console.log(UserAccountService.ParseJwt(UserAccountService.GetJwt()!))
    if (UserAccountService.IsJwtExpired()) {
      console.log("token has expired, logged out");
      UserAccountService.Logout();
    }
    request.headers = {
      Authorization: "Bearer " + UserAccountService.GetJwt(),
    };
    return request;
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

  async AddEmployee(departmentName: string, employeeData: Employee) {
    const body = {
      name: employeeData.name,
      surname: employeeData.surname,
      departmentName: departmentName,
    };
    return await axiosWithTokenCheck.post(`${baseUrl}/employees/add`, body);
  },
  async DeleteEmployee(name: string, surname: string, departmentName: string) {
    const body: DeleteEmployeeRequest = {
      name: name,
      surname: surname,
      departmentName: departmentName,
    };
    return await axiosWithTokenCheck.delete(`${baseUrl}/employees/delete`, {
      data: body,
    });
  },

  async SendRegisterRequest(request: RegisterRequest) {
    return await axios.post(`${baseUrl}/users/register`, request);
  },

  async SendLoginRequest(request: LoginRequest) {
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
    const instance = axios.create();
    const res = await instance.get(`${baseUrl}/users/token/refresh`, {
      headers: {
        Authorization: "Bearer " + UserAccountService.GetRefreshToken()!,
      },
    });
  },
};
export default ApiService;
