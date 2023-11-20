<script setup>
import { ref } from "vue";
import { RouterView, RouterLink, useRouter } from "vue-router";
const link = ref("list");
</script>

<template>
  boardview
  <router-view></router-view>
</template>

<style scoped></style>
