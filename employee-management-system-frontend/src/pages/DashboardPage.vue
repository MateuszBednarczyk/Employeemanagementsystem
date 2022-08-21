<template>
  <q-layout view="hHh lpR fFf">
    <q-header bordered class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title> Employee Management Service </q-toolbar-title>
        <q-btn
          align="right"
          color="grey-1"
          text-color="primary"
          @click="onLogout"
        >
          Logout
        </q-btn>
        <q-btn color="primary" icon="check" label="Test" @click="onTestButton" />
      </q-toolbar>

      <q-tabs align="left" v-model="tab" inline-label>
        <q-tab
          v-for="department in departments"
          :name="department"
          :label="department"
        />
      </q-tabs>
    </q-header>

    <q-page-container>
      <q-tab-panels v-model="tab">
        <q-tab-panel v-for="department in departments" :name="department">
          <EmployeeList :department-name="department.departmentName"/>
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
import ApiService from "@/services/ApiService";
import UserAccountService from "@/services/UserAccountService";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import EmployeeList from "@/components/EmployeeList.vue";
const router = useRouter();
const departments = ref<any[]>([]);

const tab = ref("employees");

onMounted(() => {
  if (!UserAccountService.IsLogged()) {
    router.push("/login");
  }

  ApiService.getDepartments().then((res) => {
    console.log(res.data);
    res.data.forEach((el: any) => {
      departments.value.push({departmentName: el.departmentName});
    });
    tab.value = departments.value[0];
  });
});

const onTestButton = () => {
  ApiService.refreshToken().then(
    res => console.log(res)
  )
}

const onLogout = async () => {
  await UserAccountService.Logout();
};
</script>

<style lang="scss"></style>
