<template>
  <q-drawer
    class="sidebar bg-accent"
    side="left"
    v-model="showSidebar"
    :mini="!sidebarExpanded"
    elevated
    :width="200"
    :mini-width="70"
    :breakpoint="500"
  >
    <SidebarToggleButton @toggle="toggleSidebar"></SidebarToggleButton>
    <q-list padding>
      <SidebarItem
        v-if="showEmployees"
        :is-expanded="sidebarExpanded"
        name="Employees"
        path="employees"
      ></SidebarItem>
      <SidebarItem
        v-if="showDepartments"
        :is-expanded="sidebarExpanded"
        name="Departments"
        path="departments"
      ></SidebarItem>
      <SidebarItem
        v-if="showModerators"
        :is-expanded="sidebarExpanded"
        name="Moderators"
        path="moderators"
      ></SidebarItem>
      <SidebarItem
        v-if="showAdmins"
        :is-expanded="sidebarExpanded"
        name="Admins"
        path="admins"
      ></SidebarItem>
    </q-list>
  </q-drawer>
</template>

<script setup lang="ts">
import { Roles } from "@/models/enums/roles";
import UserAccountService from "@/services/UserAccountService";
import { ref } from "vue";
import SidebarItem from "./SidebarItem.vue";
import SidebarToggleButton from "./SidebarToggleButton.vue";

const showSidebar = ref(true);
const sidebarExpanded = ref(false);

const showEmployees = ref(false);
const showDepartments = ref(false);
const showModerators = ref(false);
const showAdmins = ref(false);

const role = UserAccountService.GetRole();

if (role == Roles.Moderator) {
  showEmployees.value = true;
} else if (role == Roles.Admin) {
  console.log("a");
  showEmployees.value = true;
  showDepartments.value = true;
  showModerators.value = true;
} else if (role == Roles.SuperAdmin) {
  showEmployees.value = true;
  showDepartments.value = true;
  showModerators.value = true;
  showAdmins.value = true;
} else {
  console.error("unknown role: " + role);
  UserAccountService.Logout();
}

const toggleSidebar = () => {
  sidebarExpanded.value = !sidebarExpanded.value;
};
</script>

<style lang="scss" scoped></style>
