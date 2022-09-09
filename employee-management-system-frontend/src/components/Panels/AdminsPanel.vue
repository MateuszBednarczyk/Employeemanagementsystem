<template>
  <q-page padding>
    <!-- <div>Moderators panel</div> -->
    <div class="table-container">
      <q-btn
        color="primary"
        icon="add"
        label="Add new admin"
        @click="openAddModeratorDialog"
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
            <q-td key="department" :props="props">
              {{ rowDepartmentsToString(props.row.departments) }}
            </q-td>
            <q-td key="actions" :props="props" auto-width>
              <div class="table-actions-container">
                <q-btn
                  color="primary"
                  icon="edit"
                  size="md"
                  @click="editModerator(props.rowIndex)"
                >
                  <q-tooltip :delay="500"> Edit </q-tooltip>
                </q-btn>
                <q-btn
                  color="negative"
                  icon="delete"
                  size="md"
                  @click="deleteModerator(props.rowIndex)"
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

  <q-dialog v-model="addModeratorDialogOpened" @before-show="beforeShowDialog">
    <q-card class="dialog-card">
      <q-form class="" @submit="submitAddModerator">
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
  reloadModerators();
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

const reloadModerators = () => {
  ApiService.GetAdmins().then((res) => {
    const data: ModeratorTableRow[] = res.data.map((el: any) => {
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
const addModeratorDialogOpened = ref(false);
const editModeratorDialogOpened = ref(false);

const dialogUsername = ref("");
const dialogEmail = ref("");
const dialogPassword = ref("");
const dialogDepartment = ref("");

const departmentNames = ref<string[]>([]);
const selectedModerator = ref<ModeratorTableRow | null>(null);

const newDepartmentName = ref<string | null>(null);
const newDepartments = ref<string[]>([]);

const openAddModeratorDialog = () => {
  addModeratorDialogOpened.value = true;
};
const closeAddModeratorDialog = () => {
  addModeratorDialogOpened.value = false;
};

const openEditModeratorDialog = (moderator: ModeratorTableRow) => {
  selectedModerator.value = moderator;
  editModeratorDialogOpened.value = true;
};

const closeEditModeratorDialog = () => {
  editModeratorDialogOpened.value = false;
  selectedModerator.value = null;
  clearFields();
};

const beforeShowDialog = () => {
  if (selectedModerator.value == null) return;
  clearFields();
};
const clearFields = () => {
  dialogUsername.value = "";
  dialogEmail.value = "";
  dialogPassword.value = "";
  newDepartmentName.value = null;
  newDepartments.value = [];
};

const submitAddModerator = () => {
  const requestData: AddModeratorRequest = {
    username: dialogUsername.value,
    email: dialogEmail.value,
    password: dialogPassword.value,
  };
  ApiService.AddAdmin(requestData).then(() => {
    reloadModerators();
    closeAddModeratorDialog();
  });
};

const submitEditModerator = () => {
  if (newDepartments.value.length <= 0) {
    alert("no departments selected");
    return;
  }
  ApiService.AddModeratorToDepartments(
    selectedModerator.value?.username!,
    newDepartments.value
  ).then(() => {
    reloadModerators();
    closeEditModeratorDialog();
  });
};

const dialogSelectNewDepartment = () => {
  if (newDepartmentName.value == null) {
    alert("no department selected");
    return;
  }
  let moderatorDepartmentNames = null;
  if (selectedModerator.value?.departments) {
    moderatorDepartmentNames = selectedModerator.value?.departments.map(
      (dep) => dep.departmentName
    );
  }

  if (
    moderatorDepartmentNames != null &&
    moderatorDepartmentNames.includes(newDepartmentName.value)
  ) {
    alert("moderator is already assigned to this department");
    return;
  }
  if (newDepartments.value.includes(newDepartmentName.value)) {
    alert("this department is already selected");
    return;
  }
  newDepartments.value.push(newDepartmentName.value);
};

const removeNewDepartment = (departmentName: string) => {
  newDepartments.value = newDepartments.value.filter(
    (dep) => dep != departmentName
  );
};

const removeModeratorFromDepartment = (departmentName: string) => {
  ApiService.RemoveModeratorFromDepartment(
    selectedModerator.value?.username!,
    departmentName
  ).then(() => {
    closeEditModeratorDialog();
    reloadModerators();
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

const deleteModerator = (rowId: number) => {
  ApiService.DeleteModerator(rows.value[rowId].username).then(() => {
    reloadModerators();
  });
};

const editModerator = (rowId: number) => {
  ErrorService.functionalityNotImplemented();
  const rowProxy = rows.value[rowId];
  const moderator = toRaw(rowProxy);
  openEditModeratorDialog(moderator);
};

//#endregion

//#region Table data

const columns: any = [
  {
    name: "username",
    required: true,
    label: "Username",
    align: "left",
    field: (row: ModeratorTableRow) => row.username,
    sortable: true,
  },
  {
    name: "email",
    align: "left",
    label: "Email",
    field: (row: ModeratorTableRow) => row.email,

    sortable: true,
  },
  {
    name: "department",
    label: "Department(s)",
    align: "left",
    sortable: false,
  },
  {
    name: "actions",
    label: "Actions",
    align: "center",
  },
];

const rows = ref<ModeratorTableRow[]>([]);

//#endregion

interface ModeratorTableRow {
  username: string;
  email: string;
  role: string;
  departments: Department[];
}
</script>

<style lang="scss">
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

.edit-moderator-card {
  padding: 30px;
  width: 30vw;
  height: 50vh;
}
</style>
