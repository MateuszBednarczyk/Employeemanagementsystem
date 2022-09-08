import { Roles } from "@/models/enums/roles";
import { DeleteEmployeeRequest } from "@/models/requests/delete-employee-request";
import LoginRequest from "@/models/requests/login-request";
import axios from "axios";
import UserAccountService from "./UserAccountService";

const baseUrl = "http://localhost:8080/api";

const axiosWithTokenCheck = axios.create();

export const httpInterceptor = axiosWithTokenCheck.interceptors.request.use(
  async (request) => {
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
  //#region Departments
  async AddDepartment(departmentName: string) {
    const body = { departmentName: departmentName };
    return await axiosWithTokenCheck.post(`${baseUrl}/department/add`, body);
  },

  async GetDepartments() {
    return await axiosWithTokenCheck.get(`${baseUrl}/department/`);
  },
  async DeleteDepartment(departmentName: string) {
    return await axiosWithTokenCheck.delete(
      `${baseUrl}/department/delete/${departmentName}`
    );
  },
  //#endregion

  //#region Employees

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

  //#endregion

  //#region Moderators

  async AddModerator(request: AddModeratorRequest) {
    const registerRequest: RegisterRequest = {
      ...request,
      role: Roles.Moderator,
    };
    await axiosWithTokenCheck.post(
      `${baseUrl}/users/register`,
      registerRequest
    );
  },

  GetModerators() {
    return axiosWithTokenCheck.get(`${baseUrl}/users/moderators`);
  },

  DeleteModerator(username: string) {
    const body = { username: username };
    return axiosWithTokenCheck.delete(`${baseUrl}/users/delete`, {
      data: body,
    });
  },

  async AddModeratorToDepartments(username: string, departmentNames: string[]) {
    for (let i = 0; i < departmentNames.length; i++) {
      const dep = departmentNames[i];
      await axiosWithTokenCheck.post(`${baseUrl}/department/add-moderator`, {
        username: username,
        departmentName: dep,
      });
    }
  },
  //#endregion

  //#region Admins

  //#endregion

  //#region Login

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

  //#endregion

  //#region Refresh token

  async refreshToken() {
    const instance = axios.create();
    const res = await instance.get(`${baseUrl}/users/token/refresh`, {
      headers: {
        Authorization: "Bearer " + UserAccountService.GetRefreshToken()!,
      },
    });
  },

  //#endregion
};
export default ApiService;
