<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-toolbar-title> Admin Panel </q-toolbar-title>
        <q-btn
          align="right"
          color="grey-1"
          text-color="primary"
          @click="onLogout"
        >
          Logout
        </q-btn>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <q-page style="padding: 20px 100px">
        <div class="row">
          <div class="col-auto">
            <h4 class="custom-heading">Departments</h4>
          </div>
          <div class="add-department-btn-container">
            <q-btn
              size="sm"
              color="primary"
              icon="add"
              @click="openAddDepartmentDialog"
            />
          </div>
        </div>
        <div class="row">
          <q-list class="col-4">
            <q-item v-for="department in departments" style="padding-left: 0">
              <q-item-section>
                <div class="row justify-around" style="height: fit-content">
                  <div class="col" style="max-width: fit-content">
                    <q-btn
                      class="edit-department-button"
                      size="sm"
                      color="primary"
                      icon="edit"
                      @click="
                        openEditDepartmentDialog(department.departmentName)
                      "
                    />
                  </div>
                  <div class="col">
                    <q-expansion-item
                      expand-separator
                      icon="work"
                      :label="department.departmentName"
                    >
                      <q-list bordered>
                        <q-item
                          v-for="employee in department.employees"
                          caption="hello"
                        >
                          <q-item-section>
                            <q-item-label>{{ employee.name }}</q-item-label>
                            <q-item-label caption>
                              <!-- {{employee.role}} -->
                              role goes here
                            </q-item-label>
                          </q-item-section>
                        </q-item>
                        <q-item v-if="!department.employees || department.employees.length <= 0">
                          <q-item-section>
                            <q-item-label>No employees</q-item-label>
                          </q-item-section>
                        </q-item>
                      </q-list>
                    </q-expansion-item>
                  </div>
                </div>
              </q-item-section>
            </q-item>
          </q-list>
        </div>
      </q-page>
    </q-page-container>

    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <q-toolbar-title>
          <div>Employee Management System</div>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>
  </q-layout>

  <add-department-dialog
    :opened="addDepartmentDialogOpened"
    @dialog-closed="closeAddDepartmentDialog"
  />
  <edit-department-dialog
    :opened="editDepartmentDialogOpened"
    :department-name="selectedDepartment!"
    @dialog-closed="closeEditDepartmentDialog"
  />
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import ApiService from "@/services/ApiService";
import EditDepartmentDialog from "./EditDepartmentDialog.vue";
// import AddDepartmentDialog from "./AddDepartmentDialog.vue";
import UserAccountService from "@/services/UserAccountService";
onMounted(() => {
  ApiService.getDepartments().then((res) => {
    departments.value = res.data;
  });
});
let departments = ref<Department[]>();

const addDepartmentDialogOpened = ref(false);
const editDepartmentDialogOpened = ref(false);
const selectedDepartment = ref<string | null>(null);

const openAddDepartmentDialog = () => {
  addDepartmentDialogOpened.value = true;
};
const closeAddDepartmentDialog = () => {
  addDepartmentDialogOpened.value = false;
};

const openEditDepartmentDialog = (departmentName: string) => {
  selectedDepartment.value = departmentName;
  editDepartmentDialogOpened.value = true;
  // console.log('opened edit department dialog with ' + selectedDepartment.value)
};

const closeEditDepartmentDialog = () => {
  editDepartmentDialogOpened.value = false;
  selectedDepartment.value = null;
};

const onLogout = async () => {
  await UserAccountService.Logout();
};
</script>

<style lang="scss" scoped>
.custom-heading {
  margin: 10px 0;
  font-weight: 350;
  font-size: x-large;
}
.edit-department-button {
  padding: 0 10px;
  margin-top: 12px;
  margin-right: 15px;
}

.add-department-btn-container {
  display: flex;
  align-items: center;
  width: fit-content;
  margin-left: 20px;
  button {
    padding: 0 5px;
  }
}
</style>
