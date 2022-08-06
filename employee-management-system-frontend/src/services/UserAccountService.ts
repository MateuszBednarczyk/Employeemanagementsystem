import RegisterRequestDto from "@/models/register-request-dto";
import { useRouter } from "vue-router";
import ApiService from "./ApiService";
import router from "@/router";


const UserAccountService = {
  IsLogged(): boolean {
    //TODO: use cookies or something better
    return localStorage.getItem("isLogged") == "true";
  },

  async Login() {
    localStorage.setItem("isLogged", "true");
    router.push('/dashboard')
  },
  Logout() {
    localStorage.setItem("isLogged", "false");
    router.push('/login')
  },

  async Register(request: RegisterRequestDto) {
    ApiService.SendRegisterRequest(request).then((res) => {
      if(res.status === 200){
        localStorage.setItem("isLogged", "true")
        router.push('/dashboard')
      }
    });
  },
};

export default UserAccountService;
