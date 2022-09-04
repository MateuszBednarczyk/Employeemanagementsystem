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

  <q-dialog v-model="addEmployeeDialogOpened" @before-show="beforeShowDialog">
    <q-card class="dialog-card">
      <q-form class="" @submit="submitAddEmployee">
        <q-card-section>
          <div class="text-h6">
            Add employee to {{ selectedDepartment?.departmentName }} department
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input
            v-model="firstName"
            type="text"
            label="first name"
            :rules="[(val) => !!val || 'Field is required']"
          />
          <q-input
            v-model="surname"
            type="text"
            label="surname"
            :rules="[(val) => !!val || 'Field is required']"
          />
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="Cancel" color="primary" v-close-popup />
          <q-btn flat label="Submit" type="submit" color="primary" />
        </q-card-actions>
      </q-form>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import ApiService from "@/services/ApiService";
import { ErrorService } from "@/services/ErrorService";
import { onMounted, ref } from "vue";
import AddEmployeeDialog from "../AddEmployeeDialog.vue";

//#region Dialog handling

const addEmployeeDialogOpened = ref(false);
const firstName = ref("");
const surname = ref("");

const openAddEmployeeDialog = () => {
  addEmployeeDialogOpened.value = true;
};
const closeAddEmployeeDialog = () => {
  addEmployeeDialogOpened.value = false;
};

const beforeShowDialog = () => {
  firstName.value = "";
  surname.value = "";
};

const submitAddEmployee = () => {
  ApiService.AddEmployee(selectedDepartment.value?.departmentName!, {
    name: firstName.value!,
    surname: surname.value!,
  }).then(() => {
    closeAddEmployeeDialog();
    reloadEmployees();
  });
};

//#endregion

const departmentSelectModel = ref<string>("");
const departmentNames = ref<string[]>([]);

const selectedDepartment = ref<Department>();

const departments = ref<Department[]>([]);

onMounted(() => {
  reloadEmployees();
});

//#region Handling department changes

const reloadEmployees = () => {
  ApiService.GetDepartments().then((res) => {
    departments.value = [];
    departmentNames.value = [];
    const data: Department[] = res.data;
    if (data[0].departmentName == "superadmin") {
      data.shift();
    }
    data.forEach((element) => {
      departments.value.push({
        departmentName: element.departmentName,
        employeesList: element.employeesList,
      });

      departmentNames.value.push(element.departmentName);
    });
    console.log(departmentNames)
    if (departmentSelectModel.value == "") {
      departmentSelectModel.value = departmentNames.value.at(0)!;
    }
    selectDepartment(departmentSelectModel.value);
  });
};

const selectDepartment = (department: string) => {
  const index = departments.value.findIndex(
    (dep) => dep.departmentName == department
  );
  // reloadEmployees()
  changeDepartment(index);
};

const changeDepartment = (index: number) => {
  selectedDepartment.value = departments.value[index];
  rows.value = departments.value[index].employeesList;
};
//#endregion

//#region Table actions

const editEmployee = (rowIndex: number) => {
  ErrorService.functionalityNotImplemented();
};

const deleteEmployee = (rowIndex: number) => {
  const row = rows.value[rowIndex];
  ApiService.DeleteEmployee(
    row.name,
    row.surname,
    selectedDepartment.value?.departmentName!
  ).then(() => {
    reloadEmployees();
  });
};

//#endregion

//#region Table data
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

const rows = ref<EmployeeTableRow[]>([]);

//#endregion

interface EmployeeTableRow {
  name: string;
  surname: string;
}
</script>

<style lang="scss"></style>
