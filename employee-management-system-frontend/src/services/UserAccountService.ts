import RegisterRequestDto from "@/models/register-request-dto";
import { useRouter } from "vue-router";
import ApiService from "./ApiService";
import router from "@/router";
import LoginRequestDto from "@/models/login-request-dto";

const UserAccountService = {
  IsLogged(): boolean {
    //TODO: use cookies or something better
    return this.GetJwt() != null;
  },

  async Login(username: string, password: string) {

    if(this.IsLogged()){
      router.push("/dashboard");
      return
    }

    const loginRequest: LoginRequestDto = {
      username: username,
      password: password,
    };

    ApiService.SendLoginRequest(loginRequest).then((res) => {
      if (res.status === 200) {
        const data: LoginResponse = res.data;
        this.SetJwt(data.access_token);
        
        localStorage.setItem('refreshToken', data.refresh_token)

        this.SetUsername(res.data.user.username)
        router.push("/dashboard");
      }
    });
  },
  Logout() {
    // this.ClearJwt()
    localStorage.clear()
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

  SetJwt(token: string) {
    localStorage.setItem("jwt", token);
  },
  GetJwt(): string | null {
    return localStorage.getItem("jwt");
  },
  ClearJwt(){
    localStorage.removeItem('jwt')
  },

  SetUsername(username:string){
    localStorage.setItem('username', username)
  },
  GetUsername(){
    return localStorage.getItem('username')
  },
  ClearUsername(){
    localStorage.removeItem('username')
  }
};

export default UserAccountService;
