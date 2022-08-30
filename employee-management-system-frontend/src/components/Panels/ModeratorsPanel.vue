<template>
  <q-page padding>
    <!-- <div>Moderators panel</div> -->
    <div class="table-container">
      <q-btn color="primary" icon="add" label="Add new moderator" @click="addModerator" />
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
            <q-td key="name" :props="props">
              {{ props.row.name }}
            </q-td>
            <q-td key="surname" :props="props">
              {{ props.row.surname }}
            </q-td>
            <q-td key="department" :props="props">
              {{ props.row.department }}
            </q-td>
            <q-td key="actions" :props="props" auto-width>
              <div class="table-actions-container">
                <!-- <q-btn color="primary" icon="search" size="md" @click="viewModerator(props.rowIndex)">
                  <q-tooltip :delay="500"> Details </q-tooltip>
                </q-btn> -->
                <q-btn color="primary" icon="edit" size="md" @click="editModerator(props.rowIndex)">
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
</template>

<script setup lang="ts">
const columns: any = [
  {
    name: "name",
    required: true,
    label: "Name",
    align: "left",
    field: (row: ModeratorTableRow) => row.name,
    sortable: true,
  },
  {
    name: "surname",
    align: "left",
    label: "Surname",
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

const rows: ModeratorTableRow[] = [
  {
    name: "adam",
    surname: "abacki",
    department: "helpdesk",
  },
  {
    name: "bartek",
    surname: "babacki",
    department: "helpdesk",
  },
  {
    name: "cezary",
    surname: "cabacki",
    department: "HR",
  },
  {
    name: "darek",
    surname: "dabacki",
    department: "IT",
  },
];

const addModerator = () => {

}


const deleteModerator = (rowId: number) => {
  console.log("trying to delete row " + rowId);
  console.log(rows[rowId]);
};

const editModerator = (rowId: number) => {
  console.log("trying to edit row " + rowId);
  console.log(rows[rowId]);
};

interface ModeratorTableRow {
  name: string;
  surname: string;
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

  button{
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
