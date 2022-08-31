<template>
  <div class="container">
    <q-card class="my-card" color="primary">
      <q-card-section>
        <div class="text-subtitle1">Welcome To</div>
        <div class="text-h6">Employee Management Service</div>
      </q-card-section>
      <q-card-actions horizontal align="center">
        <q-btn flat label="Login" @click="openLoginDialog" />
      </q-card-actions>

      <q-dialog v-model="loginDialogOpen" :persistent="false">
        <q-card>
          <q-card-section class="row items-center">
            <div class="col">
              <div class="row">
                <q-input v-model="username" type="text" label="username" />
              </div>
              <div class="row">
                <q-input v-model="password" type="text" label="password" />
              </div>
            </div>
          </q-card-section>
          <q-card-actions align="right">
            <q-btn flat label="Cancel" color="primary" v-close-popup />
            <q-btn
              flat
              label="Submit"
              color="primary"
              @click="login"
              v-close-popup
            />
          </q-card-actions>
        </q-card>
      </q-dialog>
    </q-card>
  </div>
</template>

<script setup lang="ts">
import UserAccountService from "@/services/UserAccountService";
import { ref } from "vue";

const loginDialogOpen = ref(false);

const username = ref("");
const password = ref("");

const login = () => {
  UserAccountService.Login(username.value, password.value);
};

const openLoginDialog = () => {
  loginDialogOpen.value = true;
};
</script>

<style lang="scss" scoped>
.my-card {
  max-width: 400px;
  background-color: $primary;
  color: white;
  text-align: center;
}

.container {
  width: 100%;
  height: 100vh;

  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
