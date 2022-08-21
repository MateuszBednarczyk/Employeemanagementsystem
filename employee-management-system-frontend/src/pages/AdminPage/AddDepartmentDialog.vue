<template>
  <q-dialog v-model="props.opened" @before-show="onBeforeShow">
    <q-card class="dialog-card">
      <q-card-section>
        <div class="text-h6">Add department</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-form class="q-gutter-md">
          <q-input
            v-model="departmentName"
            type="text"
            label="department name"
          />
        </q-form>
        <!-- <Spacer width="0" height="30px" />
        <span>Moderators</span>
        <q-list>
          <q-item>
            <q-item-section>
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
          </q-item>
        </q-list> -->
      </q-card-section>

      <q-card-actions align="right">
        <q-btn
          flat
          label="Cancel"
          color="primary"
          v-close-popup
          @click="onCloseDialog"
        />
        <q-btn
          flat
          label="Submit"
          color="primary"
          v-close-popup
          @click="onSubmit"
        />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Spacer from "@/components/Spacer.vue";
import ApiService from "@/services/ApiService";
import router from "@/router";
// const opened = false;
const props = defineProps({
  opened: Boolean,
});

const emits = defineEmits(["dialogClosed"]);

const departmentName = ref<string | null>(null);

const onCloseDialog = () => {
  emits("dialogClosed");
};

const onBeforeShow = () => {
  departmentName.value = null;
};

const onSubmit = () => {
  console.log('adding department')
  ApiService.AddDepartment(departmentName.value!).then(() => {
    router.go(0);
  });
};
</script>

<style lang="scss"></style>
