<template>
    <q-item clickable @click="toggle" class="button-item">
        <q-item-section top avatar>
            <q-avatar color="primary" text-color="white" :icon="buttonIcon" />
        </q-item-section>
    </q-item>
  <!-- <div class="button-container">
    <q-btn color="primary" :icon="buttonIcon" @click="toggle" />
  </div> -->
</template>

<script setup lang="ts">
import { ref } from 'vue';

const expanded = ref(false)
const buttonIcon = ref("chevron_right")

const emit = defineEmits(['toggle'])
const toggle = () => {
    expanded.value = !expanded.value

    if(expanded.value){
        buttonIcon.value = "chevron_left"
    }else{
        buttonIcon.value = "chevron_right"
    }
    emit('toggle')
}
</script>

<style lang="scss" scoped>
.button-container {
//   margin: 10px 0;
//   padding: 0 5px;
  box-sizing: border-box;

  button {
    width: 65px;
  }
}

.button-item{
    width: 100%;
    margin-bottom: 20px;
}
</style>
