import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import DashboardPage from "@/pages/DashboardPage.vue";

import AdminPage from "@/pages/AdminPage/AdminPage.vue";
import LoginPage from "@/pages/LoginPage.vue";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/dashboard",
    name: "dashboard",
    component: DashboardPage,
  },
  {
    path: "/login",
    name: "login",
    component: LoginPage,
  },
  {
    path: "/admin",
    name: "admin",
    component: AdminPage,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
