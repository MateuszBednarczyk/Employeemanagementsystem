<template>
  <!-- <div
    class="sidebar-item-container"
    :class="{ 'sidebar-item-container-mini': !props.isExpanded }"
  >
    <div
      class="sidebar-item shadow-3"
      :class="{ 'sidebar-item-hidden': !props.isExpanded }"
    >
      <div class="icon"><img src="@/assets/icons/employee.png" alt="" /></div>
      <div v-if="props.isExpanded" class="text-container">
        <span class="text-white">expanded</span>
      </div>
    </div>
  </div> -->
  <q-item clickable :to="props.path">
    <q-item-section avatar>
      <q-avatar color="primary" text-color="white" :icon="iconPath" />
    </q-item-section>
    <q-item-section>
      <q-item-label class="text-subtitle">{{ props.name }}</q-item-label>
    </q-item-section>
  </q-item>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';

const iconPath = ref('')
const props = defineProps({
  isExpanded: Boolean,
  name: String,
  path: String,
});
onMounted(()=> {
  if(props.path == 'employees'){
    iconPath.value = 'img:icons/employee.png'
  }else if(props.path == 'departments'){
    iconPath.value = 'img:icons/department.png'
  }else if(props.path == 'moderators'){
    iconPath.value = 'img:icons/moderator.png'
  }else if(props.path == 'admins'){
    iconPath.value = 'img:icons/admin.png'
  }
})
</script>

<style lang="scss" scoped>
.sidebar-item-container {
  width: 100%;
  height: 75px;

  padding: 0 5px;
  box-sizing: border-box;

  display: flex;
  justify-content: center;
  align-items: center;
}
.sidebar-item-container-mini {
  width: 75px;
}
.sidebar-item {
  width: 100%;
  height: 80%;
  padding: 0 5px;
  background-color: $primary;
  border-radius: 5px;

  display: flex;
  align-items: center;
  justify-content: center;
  .icon {
    // background-color: $accent;
    width: 50px;
    height: 50px;
    min-width: 50px;
    img {
      padding: 5px;
      width: 100%;
      height: 100%;
    }
  }

  .text-container {
    // background-color: $accent;

    margin-left: 15px;
    flex-grow: 1;
    display: flex;
    text-align: center;
    // justify-items: center;
    align-items: center;

    span {
      width: 100%;
      // text-align: center;
      font-size: x-large;
    }
  }
}
</style>
