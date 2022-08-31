<template>
  <q-item clickable @click="toggle" class="button-item">
    <q-item-section top avatar>
      <q-avatar color="primary" text-color="white" :icon="buttonIcon" />
    </q-item-section>
  </q-item>
</template>

<script setup lang="ts">
import { ref } from "vue";

const expanded = ref(false);
const buttonIcon = ref("chevron_right");

const emit = defineEmits(["toggle"]);
const toggle = () => {
  expanded.value = !expanded.value;

  if (expanded.value) {
    buttonIcon.value = "chevron_left";
  } else {
    buttonIcon.value = "chevron_right";
  }
  emit("toggle");
};
</script>

<style lang="scss" scoped>
.button-container {
  box-sizing: border-box;

  button {
    width: 65px;
  }
}

.button-item {
  width: 100%;
  margin-bottom: 20px;
}
</style>
