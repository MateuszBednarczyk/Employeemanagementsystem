import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import DashboardPage from "@/pages/DashboardPage.vue";

import AdminPage from "@/pages/AdminPage/AdminPage.vue";
import LoginPage from "@/pages/LoginPage.vue";
import EmployeesPanel from "@/components/Panels/EmployeesPanel.vue";
import DepartmentsPanel from "@/components/Panels/DepartmentsPanel.vue";
import ModeratorsPanel from "@/components/Panels/ModeratorsPanel.vue";
import AdminsPanel from "@/components/Panels/AdminsPanel.vue";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/dashboard",
    name: "dashboard",
    component: DashboardPage,
    children: [
      {
        path: "/employees",
        component: EmployeesPanel
      },
      {
        path: "/departments",
        component: DepartmentsPanel 
      },
      {
        path: "/moderators",
        component: ModeratorsPanel
      },
      {
        path: "/admins",
        component: AdminsPanel
      }
    ]
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
