import RegisterRequestDto from "@/models/register-request-dto";
import { useRouter } from "vue-router";
import ApiService from "./ApiService";
import router from "@/router";
import LoginRequestDto from "@/models/login-request-dto";

const UserAccountService = {
  IsLogged(): boolean {
    //TODO: use cookies or something better
    return localStorage.getItem("isLogged") == "true";
  },

  async Login(username: string, password: string) {
    const loginRequest: LoginRequestDto = {
      username: username,
      password: password,
    };

    ApiService.SendLoginRequest(loginRequest)
    .then(res=> {
      console.log(res)
    })
    
    // localStorage.setItem("isLogged", "true");
    // router.push("/dashboard");
  },
  Logout() {
    localStorage.setItem("isLogged", "false");
    router.push("/login");
  },

  async Register(request: RegisterRequestDto) {
    //TODO: delete
    await ApiService.AddTestDepartment(request.department);

    ApiService.SendRegisterRequest(request).then((res) => {
      if (res.status === 200) {
        localStorage.setItem("isLogged", "true");
        router.push("/dashboard");
      }
    });
  },
};

export default UserAccountService;
