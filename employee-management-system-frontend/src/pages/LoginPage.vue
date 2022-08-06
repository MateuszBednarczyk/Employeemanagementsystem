<template>
  <div class="container">
    <!-- <q-btn @click="login"> Login </q-btn> -->
    <q-card class="my-card" color="primary">
      <q-card-section>
        <div class="text-subtitle1">Welcome To</div>
        <div class="text-h6">Employee Management Service</div>
      </q-card-section>
      <q-card-actions horizontal align="center">
        <q-btn flat label="Login" @click="login"/>
        <q-btn flat label="Register" @click="register"/>
      </q-card-actions>
    </q-card>
  </div>
</template>

<script setup lang="ts">
import router from "@/router";
import UserAccountService from "@/services/UserAccountService";

const login = () => {
  UserAccountService.Login();
};

const register = () => {
  router.push('/register')
}
</script>

<style lang="scss" scoped>
.my-card {
  max-width: 400px;
  background-color: $primary;
  color: white;
  text-align: center;
}

.container{
  width: 100%;
  height: 100vh;

  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
