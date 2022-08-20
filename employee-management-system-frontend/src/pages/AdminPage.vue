<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
          </q-avatar>
          Admin Panel
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <q-page padding>
        <h4 class="custom-heading">Departments:</h4>
        <q-list bordered>
          <q-expansion-item
            v-for="department in departments"
            expand-separator
            icon="work"
            :label="department.departmentName"
          >
            <q-list bordered>
              <q-item v-for="employee in department.employees" caption="hello">
                <q-item-section>
                  <q-item-label>{{ employee.name }}</q-item-label>
                  <q-item-label caption>{{employee.role}}</q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
          </q-expansion-item>
        </q-list>
      </q-page>
    </q-page-container>

    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
          </q-avatar>
          <div>Title</div>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>
  </q-layout>
</template>

<script setup lang="ts">
const departments = [
  {
    departmentName: "a",
    employees: [
      {
        name: "Andrzej",
        role: "moderator",
      },
    ],
  },
  {
    departmentName: "b",
    employees: [
      {
        name: "Marek",
        role: "admin",
      },
      {
        name: "Czarek",
        role: "admin",
      },
    ],
  },
  {
    departmentName: "c",
    employees: [
      {
        name: "Kuba",
        role: "admin",
      },
      {
        name: "Mati",
        role: "moderator",
      },
    ],
  },
];
</script>

<style lang="scss" scoped>
.custom-heading {
  margin: 10px 0;
  font-weight: 350;
  font-size: x-large;
}
.q-item{
    padding-left: 70px;
}
</style>
