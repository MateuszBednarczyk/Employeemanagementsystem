<template>
  <q-page padding>
    <div class="table-container">
      <!-- <p>{{ selectedDepartment?.departmentName }}</p> -->
      <div class="row align-center">
        <div class="column justify-center">
          <div class="row-1 items-center">
            <q-btn
              style="margin-bottom: 0px"
              color="primary"
              icon="add"
              label="Add new employee"
              @click="openAddEmployeeDialog"
            />
            <!-- <q-btn
              style="margin-bottom: 0px"
              color="primary"
              label="Change department"
              @click="functionalityNotImplemented"
            /> -->
          </div>
        </div>
        <div class="col-3" style="padding: 15px; box-sizing: border-box">
          <q-select
            v-model="departmentSelectModel"
            :options="departmentNames"
            label="Department"
            filled
            @update:model-value="selectDepartment"
          />
        </div>
      </div>

      <q-table
        title="Employees"
        :rows="rows"
        :columns="columns"
        row-key="surname"
        separator="vertical"
        :card-style="{ padding: '10px 50px' }"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="name" :props="props" class="name-td">
              {{ props.row.name }}
            </q-td>
            <q-td key="surname" :props="props" class="name-td">
              {{ props.row.surname }}
            </q-td>
            <q-td key="actions" :props="props" auto-width>
              <div class="table-actions-container">
                <q-btn
                  color="primary"
                  icon="edit"
                  size="md"
                  @click="editEmployee(props.rowIndex)"
                >
                  <q-tooltip :delay="500"> Edit </q-tooltip>
                </q-btn>
                <q-btn
                  color="negative"
                  icon="delete"
                  size="md"
                  @click="deleteEmployee(props.rowIndex)"
                >
                  <q-tooltip :delay="500"> Remove </q-tooltip>
                </q-btn>
              </div>
            </q-td>
          </q-tr>
        </template>
      </q-table>
    </div>
  </q-page>
  <!-- <add-employee-dialog
    :opened="addEmployeeDialogOpened"
    :selected-department="selectedDepartment"
    @dialog-closed="closeAddEmployeeDialog"
  >
  </add-employee-dialog> -->
  <q-dialog v-model="addEmployeeDialogOpened" @before-show="beforeShowDialog">
    <q-card class="dialog-card">
      <q-card-section>
        <div class="text-h6">
          Add employee to {{ selectedDepartment?.departmentName }} department
        </div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-form class="q-gutter-md">
          <q-input
            v-model="firstName"
            type="text"
            label="first name"
            required
          />
          <q-input v-model="surname" type="text" label="surname" required />
        </q-form>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat label="Cancel" color="primary" v-close-popup />
        <q-btn
          flat
          label="Submit"
          color="primary"
          v-close-popup
          @click="submitAddEmployee"
        />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import ApiService from "@/services/ApiService";
import { onMounted, ref } from "vue";
import AddEmployeeDialog from "../AddEmployeeDialog.vue";

// ---dialog---
const firstName = ref("");
const surname = ref("");

const beforeShowDialog = () => {
  firstName.value = "";
  surname.value = "";
};

const submitAddEmployee = () => {
  if (firstName.value == null || surname.value != null) {
  }
  ApiService.AddEmployee(selectedDepartment.value?.departmentName!, {
    name: firstName.value!,
    surname: surname.value!,
  }).then(() => {
    reloadEmployees();
  });
};

// ---dialog---

const departmentSelectModel = ref<string>("");
const departmentNames = ref<string[]>([]);

const addEmployeeDialogOpened = ref(false);

const openAddEmployeeDialog = () => {
  addEmployeeDialogOpened.value = true;
};
const closeAddEmployeeDialog = () => {
  // addEmployeeDialogOpened = false;
};

const reloadEmployees = () => {
  ApiService.getDepartments().then((res) => {
    departments.value = [];
    departmentNames.value = [];
    res.data.forEach(
      (element: { departmentName: string; employeesList: [] }) => {
        departments.value.push({
          departmentName: element.departmentName,
          employees: element.employeesList,
        });
        departmentNames.value.push(element.departmentName);
      }
    );
    console.log(departments.value)
    if (departmentSelectModel.value == "") {
      departmentSelectModel.value = departmentNames.value[1];
    }
    selectDepartment(departmentSelectModel.value);
    // console.log(departments.value);
  });
};

const columns: any = [
  {
    name: "name",
    required: true,
    label: "Name",
    align: "left",
    field: (row: EmployeeTableRow) => row.name,
    sortable: true,
  },
  {
    name: "surname",
    required: true,
    label: "Surname",
    align: "left",
    field: (row: EmployeeTableRow) => row.surname,
    sortable: true,
  },
  {
    name: "actions",
    label: "Actions",
    align: "center",
  },
];

const selectedDepartment = ref<Department>();

const departments = ref<Department[]>([]);
const rows = ref<EmployeeTableRow[]>([]);

onMounted(() => {
  reloadEmployees();
});

const editEmployee = (rowIndex: number) => {
  functionalityNotImplemented();
};
const deleteEmployee = (rowIndex: number) => {
  // functionalityNotImplemented();
  const row = rows.value[rowIndex];
  ApiService.DeleteEmployee(
    row.name,
    row.surname,
    selectedDepartment.value?.departmentName!
  ).then(() => {
    reloadEmployees();
  })
};

const functionalityNotImplemented = () => {
  alert("this functionality hasn't been implemented yet");
};

const selectDepartment = (department: string) => {
  const index = departments.value.findIndex(
    (dep) => dep.departmentName == department
  );
  changeDepartment(index);
};

const changeDepartment = (index: number) => {
  selectedDepartment.value = departments.value[index];
  rows.value = departments.value[index].employees;
  // console.log("a");
};

interface EmployeeTableRow {
  name: string;
  surname: string;
}
</script>

<style lang="scss"></style>
