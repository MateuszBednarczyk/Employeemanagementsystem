<template>
  <q-page padding>
    <div class="table-container">
      <q-btn
        color="primary"
        icon="add"
        label="Add new department"
        @click="openAddDepartmentDialog"
      />
      <q-table
        title="Departments"
        :rows="rows"
        :columns="columns"
        row-key="name"
        separator="vertical"
        :card-style="{ padding: '10px 50px' }"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="name" :props="props" class="name-td">
              {{ props.row.name }}
            </q-td>
            <q-td key="actions" :props="props" auto-width>
              <div class="table-actions-container">
                <q-btn
                  color="primary"
                  label="employees"
                  size="md"
                  @click="viewEmployees(props.rowIndex)"
                >
                  <q-tooltip :delay="500"> View employees </q-tooltip>
                </q-btn>
                <q-btn
                  color="primary"
                  icon="edit"
                  size="md"
                  @click="editDepartment(props.rowIndex)"
                >
                  <q-tooltip :delay="500"> Edit </q-tooltip>
                </q-btn>
                <q-btn
                  color="negative"
                  icon="delete"
                  size="md"
                  @click="deleteDepartment(props.rowIndex)"
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
  <q-dialog v-model="addDepartmentDialogOpened" @before-show="beforeShowDialog">
    <q-card class="dialog-card">
      <q-form @submit="submitAddDepartment">
        <q-card-section>
          <div class="text-h6">Add department</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input
            v-model="departmentName"
            type="text"
            label="department name"
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
import AddDepartmentDialog from "@/components/AddDepartmentDialog.vue";
import { onMounted, ref } from "vue";
import router from "@/router";
import { ErrorService } from "@/services/ErrorService";

onMounted(() => {
  reloadDepartments();
});

//#region Loading data
const reloadDepartments = () => {
  ApiService.getDepartments().then((res) => {
    const data: Department[] = res.data;

    let t_rows: DepartmentTableRow[] = [];
    data.forEach((el) => {
      t_rows.push({
        name: el.departmentName,
      });
      rows.value = t_rows;
    });
  });
};

//#endregion

//#region Dialog handling

const departmentName = ref("");

const addDepartmentDialogOpened = ref(false);

const beforeShowDialog = () => {
  departmentName.value = "";
};

const openAddDepartmentDialog = () => {
  addDepartmentDialogOpened.value = true;
};
const closeAddDepartmentDialog = () => {
  addDepartmentDialogOpened.value = false;
};

const submitAddDepartment = () => {
  ApiService.AddDepartment(departmentName.value).then(() => {
    closeAddDepartmentDialog();
    reloadDepartments();
  });
};

//#endregion

//#region Table actions

const deleteDepartment = (rowIndex: number) => {
  ApiService.DeleteDepartment(rows.value[rowIndex].name).then((res) => {
    router.go(0);
  });
};
const editDepartment = (rowIndex: number) => {
  ErrorService.functionalityNotImplemented();
};

const viewEmployees = (rowIndex: number) => {
  ErrorService.functionalityNotImplemented();
};

//#endregion

//#region Table data

const columns: any = [
  {
    name: "name",
    required: true,
    label: "Name",
    align: "left",
    field: (row: DepartmentTableRow) => row.name,
    sortable: true,
  },
  {
    name: "actions",
    label: "Actions",
    align: "center",
  },
];

const rows = ref<DepartmentTableRow[]>([]);

//#endregion

interface DepartmentTableRow {
  name: string;
}
</script>

<style lang="scss" scoped>
.name-td {
  width: 70%;
}
</style>
