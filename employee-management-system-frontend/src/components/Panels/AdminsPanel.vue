<template>
  <q-page padding>
    <div class="table-container">
      <q-btn
        color="primary"
        icon="add"
        label="Add new admin"
        @click="openAddAdminDialog"
      />
      <q-table
        title="Admins"
        :rows="rows"
        :columns="columns"
        row-key="name"
        separator="vertical"
        :card-style="{ padding: '10px 50px' }"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="username" :props="props">
              {{ props.row.username }}
            </q-td>
            <q-td key="email" :props="props">
              {{ props.row.email }}
            </q-td>
            <q-td key="actions" :props="props" auto-width>
              <div class="table-actions-container">
                <q-btn
                  color="primary"
                  icon="edit"
                  size="md"
                  @click="editAdmin(props.rowIndex)"
                >
                  <q-tooltip :delay="500"> Edit </q-tooltip>
                </q-btn>
                <q-btn
                  color="negative"
                  icon="delete"
                  size="md"
                  @click="deleteAdmin(props.rowIndex)"
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

  <q-dialog v-model="addAdminDialogOpened" @before-show="beforeShowDialog">
    <q-card class="dialog-card">
      <q-form class="" @submit="submitAddAdmin">
        <q-card-section>
          <div class="text-h6">Add new admin</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input
            v-model="dialogUsername"
            type="text"
            label="username"
            :rules="[(val) => !!val || 'Field is required']"
          />
          <q-input
            v-model="dialogEmail"
            type="text"
            label="email"
            :rules="[
              (val) => !!val || 'Field is required',
              (val) => validateEmail(val) || 'Email isn\'t valid',
            ]"
          />
          <q-input
            v-model="dialogPassword"
            type="password"
            label="password"
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
import { onMounted, ref, toRaw } from "vue";

onMounted(() => {
  reloadAdmins();
  fetchDepartments();
});

const rowDepartmentsToString = (departmentsProxy: any) => {
  const departments = toRaw(departmentsProxy);
  if (!departments || departments.length <= 0) {
    return "no departments";
  }
  let res = "";

  for (let index = 0; index < departments.length; index++) {
    const dep = departments[index];
    res += dep.departmentName;
    if (index < departments.length - 1) {
      res += ", ";
    }
  }
  return res;
};

//#region Fetching Data

const reloadAdmins = () => {
  ApiService.GetAdmins().then((res) => {
    const data: AdminTableRow[] = res.data.map((el: any) => {
      return {
        username: el.username,
        email: el.email,
        role: el.role.roleType,
        departments: el.departments,
      };
    });

    rows.value = data;
  });
};

const fetchDepartments = () => {
  ApiService.GetDepartments().then((res) => {
    const data: Department[] = res.data;
    if (data[0].departmentName == "superadmin") {
      data.shift();
    }

    departmentNames.value = [];

    data.forEach((el: Department) => {
      departmentNames.value.push(el.departmentName);
    });

    if (departmentNames.value.length > 0) {
      dialogDepartment.value = departmentNames.value[0];
    }
  });
};

//#endregion

//#region Handling dialog
const addAdminDialogOpened = ref(false);
const editAdminDialogOpened = ref(false);

const dialogUsername = ref("");
const dialogEmail = ref("");
const dialogPassword = ref("");
const dialogDepartment = ref("");

const departmentNames = ref<string[]>([]);
const selectedAdmin = ref<AdminTableRow | null>(null);

const newDepartmentName = ref<string | null>(null);
const newDepartments = ref<string[]>([]);

const openAddAdminDialog = () => {
  addAdminDialogOpened.value = true;
};
const closeAddAdminDialog = () => {
  addAdminDialogOpened.value = false;
};

const openEditAdminDialog = (admin: AdminTableRow) => {
  selectedAdmin.value = admin;
  editAdminDialogOpened.value = true;
};

const closeEditAdminDialog = () => {
  editAdminDialogOpened.value = false;
  selectedAdmin.value = null;
  clearFields();
};

const beforeShowDialog = () => {
  if (selectedAdmin.value == null) return;
  clearFields();
};
const clearFields = () => {
  dialogUsername.value = "";
  dialogEmail.value = "";
  dialogPassword.value = "";
  newDepartmentName.value = null;
  newDepartments.value = [];
};

const submitAddAdmin = () => {
  const requestData: AddAdminRequest = {
    username: dialogUsername.value,
    email: dialogEmail.value,
    password: dialogPassword.value,
  };
  ApiService.AddAdmin(requestData).then(() => {
    reloadAdmins();
    closeAddAdminDialog();
  });
};

const validateEmail = (value: string) => {
  if (!value) {
    return false;
  }
  const regex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
  return regex.test(value);
};

//#endregion

//#region Table actions

const deleteAdmin = (rowId: number) => {
  ApiService.DeleteAdmin(rows.value[rowId].username).then(() => {
    reloadAdmins();
  });
};

const editAdmin = (rowId: number) => {
  ErrorService.functionalityNotImplemented();
  const rowProxy = rows.value[rowId];
  const admin = toRaw(rowProxy);
  openEditAdminDialog(admin);
};

//#endregion

//#region Table data

const columns: any = [
  {
    name: "username",
    required: true,
    label: "Username",
    align: "left",
    field: (row: AdminTableRow) => row.username,
    sortable: true,
  },
  {
    name: "email",
    align: "left",
    label: "Email",
    field: (row: AdminTableRow) => row.email,

    sortable: true,
  },
  {
    name: "actions",
    label: "Actions",
    align: "center",
  },
];

const rows = ref<AdminTableRow[]>([]);

//#endregion

interface AdminTableRow {
  username: string;
  email: string;
  role: string;
  departments: Department[];
}
</script>

<style lang="scss" scoped>
.table-container {
  margin-left: auto;
  margin-right: auto;

  width: 75vw;
  padding: 0 100px;
  margin-top: 50px;

  button {
    margin-bottom: 15px;
    margin-left: 15px;
  }
}
.table-actions-container {
  width: 100%;
  //weeeird
  button:not(:last-child) {
    margin-right: 10px;
  }
}
</style>
