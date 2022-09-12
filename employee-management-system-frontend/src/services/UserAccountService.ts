import ApiService from "./ApiService";
import router from "@/router";
import LoginRequest from "@/models/requests/login-request";
import { Roles } from "@/models/enums/roles";

const UserAccountService = {
  IsLogged(): boolean {
    //TODO: use cookies or something better
    return this.GetJwt() != null;
  },

  //#region Login / Logout

  async Login(username: string, password: string) {
    const loginRequest: LoginRequest = {
      username: username,
      password: password,
    };

    ApiService.SendLoginRequest(loginRequest).then((res) => {
      if (res.status === 200) {
        const data: LoginResponse = res.data;
        this.SetJwt(data.access_token);

        this.SetRefreshToken(data.refresh_token);

        this.SetUsername(data.user.username);

        const roles: string[] = this.ParseJwt(data.access_token).roles;
        if (roles.includes(Roles.SuperAdmin)) {
          this.SetRole(Roles.SuperAdmin);
        } else if (roles.includes(Roles.Admin)) {
          this.SetRole(Roles.Admin);
        } else if (roles.includes(Roles.Moderator)) {
          this.SetRole(Roles.Moderator);
        } else {
          //if no roles are assigned
          console.error("no roles assigned, signing out...");
          this.Logout();
        }

        router.push("/employees");
      }
    });
  },
  Logout() {
    localStorage.clear();

    router.push("/login");
  },

  //#endregion


  //#region JWT

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
    if (this.GetJwt() === null) {
      return false;
    }
    const expirationDate = new Date(this.ParseJwt(this.GetJwt()!).exp * 1000);
    const currentDate = new Date(Date.now());
    return currentDate >= expirationDate;
  },

  ParseJwt(token: string) {
    var base64Url = token.split(".")[1];
    var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
    var jsonPayload = decodeURIComponent(
      window
        .atob(base64)
        .split("")
        .map(function (c) {
          return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join("")
    );

    return JSON.parse(jsonPayload);
  },

  //#endregion

  //#region Refresh token

  SetRefreshToken(token: string) {
    localStorage.setItem("refresh_token", token);
  },
  GetRefreshToken() {
    return localStorage.getItem("refresh_token");
  },

  //#endregion

  //#region User data

  SetUsername(username: string) {
    localStorage.setItem("username", username);
  },
  GetUsername() {
    return localStorage.getItem("username");
  },
  ClearUsername() {
    localStorage.removeItem("username");
  },

  SetRole(role: string) {
    localStorage.setItem("role", role);
  },
  GetRole() {
    const role = localStorage.getItem("role");
    if (!role) {
      console.error("no role assigned");
      this.Logout();
    }
    return role;
  },

  //#endregion
};

export default UserAccountService;
