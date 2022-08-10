<template>
  <div class="q-pa-md form-container">
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md register-form"
    >
      <q-input v-model="username" type="text" label="Username" />
      <q-input v-model="password" type="text" label="Password" />
      <q-input v-model="passwordRepeated" type="text" label="Repeat password" />
      <q-select
        v-model="department"
        :options="departmentOptions"
        label="Department"
        filled
      />
      <div>
        <q-btn label="Submit" type="submit" color="primary" />
        <q-btn
          label="Reset"
          type="reset"
          color="primary"
          flat
          class="q-ml-sm"
        />
      </div>
    </q-form>
  </div>
</template>

<script setup lang="ts">
import RegisterRequestDto from "@/models/register-request-dto";
import ApiService from "@/services/ApiService";
import UserAccountService from "@/services/UserAccountService";
import { onMounted, ref } from "vue";
import {Roles} from "@/models/enums/roles"

const username = ref("");
const password = ref("");
const passwordRepeated = ref("");
const department = ref("");

let departmentOptions: string[] = ["a", "b", "c"];

onMounted(()=>{
  loadDepartments()
})

const onSubmit = () => {
  if (password.value != null && password.value == passwordRepeated.value) {
    const request:RegisterRequestDto = {
    username: username.value,
    password: password.value,
    department: department.value,
    //TODO: set selected role
    role: Roles.Admin
}
    UserAccountService.Register(request)
  }
};
const onReset = () => {
  username.value = '';
  password.value = '';
  passwordRepeated.value = '';
  department.value = '';

};
const loadDepartments = async () => {
  const departments = await ApiService.getDepartments()
  console.log(departments)
}

</script>

<style lang="scss" scoped>
.form-container {
  height: 100vh;
  width: 100%;

  display: flex;
  justify-content: center;
  align-items: center;
}
.register-form {
  min-width: 400px;
  max-width: 600px;
}
</style>
