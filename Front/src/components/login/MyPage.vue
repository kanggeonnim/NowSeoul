<script setup>
import { ref } from "vue";
const ques = ref("문의하기");
const quesModal = ref(false);
const changeQues = () => {
  if (ques.value === "문의하기") {
    ques.value = "문의닫기";
  } else if (ques.value === "문의닫기") {
    ques.value = "문의하기";
  }
  quesModal.value = !quesModal.value;
};
</script>

<template>
  <div>
    <div>아이디:{{}}</div>
    <div>비번:{{}}</div>
    <div>이름:{{}}</div>
    <div>이메일:{{}}</div>
    <div>전화번호:{{}}</div>
    <button v-on:click="changeQues">{{ ques }}</button>
    <input type="text" v-if="quesModal" class="modal" />
  </div>
</template>

<style scoped>
.modal {
  width: 20vw;
  height: 20vh;
}
</style>
