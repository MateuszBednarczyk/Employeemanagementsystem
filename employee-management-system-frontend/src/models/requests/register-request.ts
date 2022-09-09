interface RegisterModeratorRequest {
  username: string;
  password: string;
  email: string;
  department: string;
  role: string;
}

interface RegisterAdminRequest{
  username: string;
  password: string;
  email: string;
  role: string;
}
