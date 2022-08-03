<template>
  <div>
    <q-btn @click="login"> Login </q-btn>
  </div>
</template>

<script setup lang="ts">
import UserAccountService from "@/services/UserAccountService";

const login = () => {
  UserAccountService.Login();
};
</script>

<style lang="scss"></style>
