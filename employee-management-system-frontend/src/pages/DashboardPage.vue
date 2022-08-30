<template>
  <q-layout view="hHh lpR fFf">
    <q-header bordered class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>Dashboard</q-toolbar-title>
        <q-btn
          align="right"
          color="grey-1"
          text-color="primary"
          @click="onLogout"
        >
          Logout
        </q-btn>
        <!-- <q-btn
          color="primary"
          icon="check"
          label="Test"
          @click="onTestButton"
        /> -->
      </q-toolbar>
    </q-header>

    <Sidebar></Sidebar>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <q-toolbar-title align="right" class="text-weight-thin">
          Copyright © 2022 &nbsp &nbsp Employee management system
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>
  </q-layout>
</template>

<script setup lang="ts">
import ApiService from "@/services/ApiService";
import UserAccountService from "@/services/UserAccountService";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
// import Sidebar from "@/components/Sidebar/Sidebar.vue";
import Sidebar from "@/components/Sidebar/Sidebar.vue";
const router = useRouter();
const departments = ref<any[]>([]);

const testBool = ref(true);

onMounted(() => {
  if (!UserAccountService.IsLogged()) {
    router.push("/login");
  }

  ApiService.getDepartments().then((res) => {
    console.log(res.data);
    res.data.forEach((el: any) => {
      departments.value.push({ departmentName: el.departmentName });
    });
    // tab.value = departments.value[0];
  });
});

// const onTestButton = () => {
//   ApiService.refreshToken().then((res) => console.log(res));
// };

const onLogout = async () => {
  await UserAccountService.Logout();
};
</script>

<style lang="scss"></style>
