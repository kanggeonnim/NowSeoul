<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
const route = useRoute();
const code = route.query.code;

const callBackServer = async () => {
  console.log(code);
  axios.post("http://192.168.130.54/user/kakao/login", code).then(async (result) => {
    console.log(result);
  });
};

onMounted(() => {
  callBackServer();
});
</script>

<template>
  <!-- <div>{{ code }}</div> -->
  <div>로딩중.</div>
</template>

<style scoped></style>
