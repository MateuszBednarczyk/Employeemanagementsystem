import RegisterRequestDto from "@/models/register-request-dto";
import ApiService from "./ApiService";

const UserAccountService = {
  IsLogged(): boolean {
    //TODO: use cookies or something better
    return localStorage.getItem("isLogged") == "true";
  },

  Login() {
    localStorage.setItem("isLogged", "true");
  },
  Logout() {
    localStorage.setItem("isLogged", "false");
  },

  async Register(request: RegisterRequestDto) {
    ApiService.SendLoginRequest(request).then((res) => console.log(res));
  },
};

export default UserAccountService;
