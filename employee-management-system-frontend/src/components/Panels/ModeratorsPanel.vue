<template>
  <q-page padding>
    <!-- <div>Moderators panel</div> -->
    <div class="table-container">
      <q-btn
        color="primary"
        icon="add"
        label="Add new moderator"
        @click="openAddModeratorDialog"
      />
      <q-table
        title="Moderators"
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
              {{ props.row.department }}
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
          <div class="text-h6">Add new moderator</div>
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
            :rules="[(val) => !!val || 'Field is required']"
          />
          <q-input
            v-model="dialogPassword"
            type="password"
            label="password"
            :rules="[(val) => !!val || 'Field is required']"
          />
          <q-select
            v-model="dialogDepartment"
            :options="departmentNames"
            label="Standard"
            filled
            required
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
import { onMounted, ref } from "vue";

onMounted(() => {
  reloadModerators();
  fetchDepartments();
});

//#region Fetching Data

const reloadModerators = () => {
  ApiService.GetModerators().then((res) => {
    const data: ModeratorTableRow[] = res.data.map((el: any) => {
      return {
        username: el.username,
        email: el.email,
        role: el.role.roleType,
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

const dialogUsername = ref("");
const dialogEmail = ref("");
const dialogPassword = ref("");
const dialogDepartment = ref("");

const departmentNames = ref<string[]>([]);

const openAddModeratorDialog = () => {
  addModeratorDialogOpened.value = true;
};
const closeAddModeratorDialog = () => {
  addModeratorDialogOpened.value = false;
};

const beforeShowDialog = () => {
  dialogUsername.value = "";
  dialogEmail.value = "";
};

const submitAddModerator = () => {
  const requestData: AddModeratorRequest = {
    username: dialogUsername.value,
    email: dialogEmail.value,
    password: dialogPassword.value,
    department: dialogDepartment.value,
  };
  ApiService.AddModerator(requestData).then(() => {
    reloadModerators();
    closeAddModeratorDialog();
  });
};

//#endregion

//#region Table actions

const deleteModerator = (rowId: number) => {
  console.log("trying to delete row " + rowId);
  console.log(rows.value[rowId]);
};

const editModerator = (rowId: number) => {
  console.log("trying to edit row " + rowId);
  console.log(rows.value[rowId]);
};

//#endregion

//#region Table data

const columns: any = [
  {
    name: "username",
    required: true,
    label: "Username",
    align: "left",
    field: (row: ModeratorTableRow) => row.name,
    sortable: true,
  },
  {
    name: "email",
    align: "left",
    label: "Email",
    field: (row: ModeratorTableRow) => row.surname,

    sortable: true,
  },
  {
    name: "department",
    label: "Department(s)",
    align: "left",
    field: (row: ModeratorTableRow) => row.department,

    sortable: true,
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
  department: string;
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
</style>
