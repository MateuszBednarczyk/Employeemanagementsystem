<template>
  <q-layout view="hHh lpR fFf">
    <q-header bordered class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>Employee management system</q-toolbar-title>
        <q-btn color="grey-1" text-color="primary" @click="onLogout">
          Logout
        </q-btn>
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
import UserAccountService from "@/services/UserAccountService";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import Sidebar from "@/components/Sidebar/Sidebar.vue";
const router = useRouter();

onMounted(() => {
  if (!UserAccountService.IsLogged()) {
    router.push("/login");
  }
});

const onLogout = async () => {
  await UserAccountService.Logout();
};
</script>

<style lang="scss">
.dialog-card {
  width: 25vw;
  padding: 0 20px;
}
</style>
