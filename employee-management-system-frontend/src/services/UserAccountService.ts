import RegisterRequest from "@/models/requests/register-request";
import ApiService from "./ApiService";
import router from "@/router";
import LoginRequest from "@/models/requests/login-request";

const UserAccountService = {
  IsLogged(): boolean {
    //TODO: use cookies or something better
    return this.GetJwt() != null;
  },

  async Login(username: string, password: string) {
    // if (this.IsLogged() && !UserAccountService.IsJwtExpired()) {
    //   router.push("/dashboard");
    //   return;
    // }

    const loginRequest: LoginRequest = {
      username: username,
      password: password,
    };

    ApiService.SendLoginRequest(loginRequest).then((res) => {
      if (res.status === 200) {
        const data: LoginResponse = res.data;
        this.SetJwt(data.access_token);

        this.SetRefreshToken(data.refresh_token);

        console.log('access token ' + data.access_token)
        console.log('refresh token ' + data.refresh_token)

        this.SetUsername(data.user.username);

        //TODO: change so the username is not hardcoded
        if(loginRequest.username === 'admin' && loginRequest.username === 'admin'){
          router.push("/admin");
        }else{
          router.push("/dashboard");
        }

      }
    });
  },
  Logout() {
    localStorage.clear();

    router.push("/login");
  },

  async Register(request: RegisterRequest) {

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
  ClearJwt() {
    localStorage.removeItem("jwt");
  },
  IsJwtExpired(): boolean {
    if(this.GetJwt() === null){
      return false
    }
    const expirationDate = new Date(this.ParseJwt(this.GetJwt()!).exp * 1000)
    const currentDate = new Date(Date.now())
    // console.log('currentDate: ' + currentDate)
    // console.log('expirationDate: ' + expirationDate)
    return currentDate >= expirationDate;
  },

  SetRefreshToken(token: string) {
    localStorage.setItem("refresh_token", token);
  },
  GetRefreshToken() {
    return localStorage.getItem("refresh_token");
  },

  SetUsername(username: string) {
    localStorage.setItem("username", username);
  },
  GetUsername() {
    return localStorage.getItem("username");
  },
  ClearUsername() {
    localStorage.removeItem("username");
  },
  ParseJwt(token:string) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  },
};

export default UserAccountService;
