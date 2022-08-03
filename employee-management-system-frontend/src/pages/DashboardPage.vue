<template>
  <div>
    <button @click="logout">logout</button>
  </div>
</template>

<script setup lang="ts">
import UserAccountService from "@/services/UserAccountService";
import { onMounted } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

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
