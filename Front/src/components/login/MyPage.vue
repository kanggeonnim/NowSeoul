<script setup>
import { ref } from "vue";
import { RouterLink, RouterView } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

import Admin from "@/components/chat/Admin.vue";
import User from "@/components/chat/User.vue";

const memberStore = useMemberStore();
const { userInfo, isValidToken } = storeToRefs(memberStore);
const { getUserInfo } = memberStore;

const admin = ref({
  id: userInfo.value.id,
  name: userInfo.value.name,
});

console.log(admin.value.id);
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
  <!-- <Admin />
  <User /> -->
  <div v-if="admin.id === 'kakao3170922753'">
    <Admin />
  </div>
  <div v-else>
    <User />
  </div>
  <router-view></router-view>
</template>

<style scoped>
.modal {
  width: 20vw;
  height: 20vh;
}
</style>
