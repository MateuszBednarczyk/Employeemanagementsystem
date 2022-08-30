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
  <add-department-dialog
    :opened="addDepartmentDialogOpened"
    @dialog-closed="closeAddDepartmentDialog"
  />
</template>

<script setup lang="ts">
import ApiService from "@/services/ApiService";
import AddDepartmentDialog from "@/components/AddDepartmentDialog.vue";
import { onMounted, ref } from "vue";
import router from "@/router";

const addDepartmentDialogOpened = ref(false);

onMounted(() => {
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
});

// const addDepartment = () => {};

const deleteDepartment = (rowIndex: number) => {
  ApiService.DeleteDepartment(rows.value[rowIndex].name).then(
    res => {
      router.go(0)
    }
  )
};
const editDepartment = (rowIndex: number) => {};

const viewEmployees = (rowIndex: number) => {};

const openAddDepartmentDialog = () => {
  addDepartmentDialogOpened.value = true;
};
const closeAddDepartmentDialog = () => {
  addDepartmentDialogOpened.value = false;
};

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

interface DepartmentTableRow {
  name: string;
}
</script>

<style lang="scss" scoped>
  .name-td{
    width: 70%;
  }
</style>
