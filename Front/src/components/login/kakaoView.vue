<script setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useRoute, useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const memberStore = useMemberStore();
const router = useRouter();
const { isLogin } = storeToRefs(memberStore);
const { userLoginKakao, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const route = useRoute();
const code = route.query.code;

// async function userConfirmKakao(sucess, fail) {
//   // console.log(code);
//   await local.post("http://192.168.130.54/user/kakao/login", code).then(sucess).catch(fail);
// }
const login = async () => {
  await userLoginKakao(code);
  let token = sessionStorage.getItem("accessToken");
  console.log(token);
  if (isLogin) {
    getUserInfo(token);
    changeMenuState();
  }
  router.push("/");
};

onMounted(() => {
  login();
});
</script>

<template>
  <div>로딩중...</div>
</template>

<style scoped></style>
