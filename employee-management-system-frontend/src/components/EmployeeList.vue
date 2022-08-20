<template>
  <div>
    Employee List for {{props.departmentName}}
    <div v-for="employee in props.employees">
        Employee: {{employee}}
    </div>
  </div>
</template>

<script setup lang="ts">

const props = defineProps({
    departmentName:String,
    employees:Array
})
</script>

<style lang="scss"></style>
