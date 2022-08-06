<template>
  <q-layout view="hHh lpR fFf">

    <q-header bordered class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          "Department" Department
        </q-toolbar-title>
        <q-btn align="right" color="grey-1" text-color="primary" @click="logout">
          Logout
        </q-btn>
      </q-toolbar>

      <q-tabs align="left" v-model="tab" inline-label>
        <q-tab name="employees" label="Employees"/>
      </q-tabs>
    </q-header>

    <q-page-container>
      <q-tab-panels v-model="tab">
        <q-tab-panel name="employees">
          Hello, this is employees tab
        </q-tab-panel>
      </q-tab-panels>
    </q-page-container>

    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <q-toolbar-title>
          <div></div>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>

  </q-layout>
</template>

<script setup lang="ts">
import UserAccountService from "@/services/UserAccountService";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

const tab = ref('employees')

onMounted(() => {
  if (!UserAccountService.IsLogged()) {
    router.push("/login");
  }
});

const logout = () => {
  UserAccountService.Logout();
};
</script>

<style lang="scss"></style>
