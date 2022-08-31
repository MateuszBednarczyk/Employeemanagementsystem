<template>
    <q-dialog v-model="props.opened" @before-show="onBeforeShow">
      <q-card class="dialog-card">
        <q-card-section>
          <div class="text-h6">Add employee to {{selectedDepartment?.departmentName}} department</div>
        </q-card-section>
  
        <q-card-section class="q-pt-none">
          <q-form class="q-gutter-md">
            <q-input
              v-model="firstName"
              type="text"
              label="first name"
              required
            />
            <q-input
              v-model="surname"
              type="text"
              label="surname"
              required
            />
          </q-form>
        </q-card-section>
  
        <q-card-actions align="right">
          <q-btn
            flat
            label="Cancel"
            color="primary"
            v-close-popup
          />
          <q-btn
            flat
            label="Submit"
            color="primary"
            v-close-popup
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </template>
  
  <script setup lang="ts">
  import { PropType, ref } from "vue";
  // import Spacer from "@/components/Spacer.vue";
  import ApiService from "@/services/ApiService";


  const props = defineProps({
    opened: Boolean,
    selectedDepartment: Object as PropType<Department>
  });
  
  const emits = defineEmits(["dialogClosed", "submit"]);
  
  const firstName = ref<string | null>(null);
  const surname = ref<string | null>(null);
  
  const onCloseDialog = () => {
    emits("dialogClosed");
  };
  
  const onBeforeShow = () => {
    firstName.value = null;
    surname.value = null
  };
  
  const onSubmit = () => {
    // if(firstName.value == null || surname.value != null)
    // ApiService.AddEmployee(props.selectedDepartment?.departmentName!, {name: firstName.value!, surname: surname.value!}).then(() => {
    // //   router.go(0);
    //   emits("submit")
    // //   emits("dialogClosed")
    // });
  };
  </script>
  
  <style lang="scss"></style>
  