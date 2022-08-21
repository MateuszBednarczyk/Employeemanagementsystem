<template>
  <q-dialog v-model="props.opened" @before-show="onBeforeShow">
    <q-card class="dialog-card">
      <q-card-section>
        <div class="text-h6" align="center">Edit department</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-form class="card-form">
          <q-input
            class="card-text-input"
            v-model="departmentName"
            type="text"
            label="department name"
          />
        </q-form>
        <!-- <Spacer width="0" height="30px" /> -->
        <!-- <span class="card-heading">Employees</span>
        <q-list>
          <q-item>
            <q-item-section class="row-12">
              <div>
                <q-btn
                  style="padding: 0 5px; margin-right: 10px"
                  size="sm"
                  color="primary"
                  icon="delete"
                  @click=""
                />
                <span>employee_placeholder</span>
              </div>
            </q-item-section>
            <q-item-section class="row-12"> </q-item-section>
          </q-item>
        </q-list> -->
      </q-card-section>
      <!-- <q-card-section>
        <span class="card-heading">Add user</span>
        <q-form class="card-form">
          <q-input class="card-text-input" v-model="firstName" type="text" label="first name" />
          <q-input class="card-text-input" v-model="lastName" type="text" label="last name" />
          <div>
            <q-radio v-model="userType" val="employee" label="employee" />
            <q-radio v-model="userType" val="moderator" label="moderator" />
            <q-radio v-model="userType" val="admin" label="admin" />
          </div>
        </q-form>
      </q-card-section> -->

      <q-card-actions>
        <div class="col-1 col-md-auto">
          <q-btn
            flat
            label="Delete"
            color="negative"
            v-close-popup
            @click="onDelete"
          />
        </div>
        <div class="col-7"></div>
        <div class="col-1 col-md-auto">
          <q-btn
            style="justify-self: end"
            flat
            label="Cancel"
            color="primary"
            v-close-popup
            @click="onCloseDialog"
          />
        </div>
        <div class="col-1 col-md-auto">
          <q-btn
            flat
            label="Submit"
            color="primary"
            v-close-popup
            @click="onSubmit"
          />
        </div>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import Spacer from "@/components/Spacer.vue";
import ApiService from "@/services/ApiService";
import router from "@/router";
import UserAccountService from "@/services/UserAccountService";
const props = defineProps({
  opened: Boolean,
  departmentName: String,
});

onMounted(() => {
  departmentName.value = props.departmentName!;
  //   console.log('department name == ' + departmentName.value)
});

const emits = defineEmits(["dialogClosed"]);

const departmentName = ref<string | null>(null);

const firstName = ref("");
const lastName = ref("");

const userType = ref("employee");

const onCloseDialog = () => {
  emits("dialogClosed");
};

const onBeforeShow = () => {
  departmentName.value = props.departmentName!;
};

const onDelete = () => {
  ApiService.DeleteDepartment(props.departmentName!).then(() => {
    router.go(0);
  });
};

const onSubmit = () => {};
</script>

<style lang="scss">
.dialog-card {
  width: 40vw;
  min-width: 350px;
  padding: 10px 30px;
}

.card-heading {
  display: block;
  text-align: center;
  font-size: 18px;
}
.card-text-input {
  width: 80%;
  margin: auto 0;
}
.card-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
