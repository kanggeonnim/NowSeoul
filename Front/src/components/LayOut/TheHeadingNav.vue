<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { RouterLink } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { KAKAO_AUTH_URL } from "@/api/kakao/KakaoAuth";

const memberStore = useMemberStore();
const modalCheck = ref(false);
const modalOpen = () => {
  modalCheck.value = !modalCheck.value;
};
const { isLogin } = storeToRefs(memberStore);
const { userLogout } = memberStore;

const kakaoLogin = () => {
  window.location.href = KAKAO_AUTH_URL;
};
</script>

<template>
  <div class="nav">
    <div class="mainnav">
      <router-link to="/" v-on:click="" style="text-decoration: none; color: rgb(255, 255, 255)"
        >HotPlace</router-link
      >
      <router-link
        to="/board/list"
        v-on:click=""
        style="text-decoration: none; color: rgb(255, 255, 255)"
        >게시판</router-link
      >
      <router-link to="/plan" v-on:click="" style="text-decoration: none; color: rgb(255, 255, 255)"
        >계획 세우기</router-link
      >
      <router-link
        to="/custom"
        v-on:click=""
        style="text-decoration: none; color: rgb(255, 255, 255)"
        >나만의 맞춤</router-link
      >
    </div>
    <div class="loginnav">
      <router-link
        to="/mypage"
        v-if="isLogin"
        v-on:click=""
        style="text-decoration: none; color: rgb(255, 255, 255)"
        >마이페이지</router-link
      >
      <router-link
        to="/join"
        v-if="!isLogin"
        v-on:click=""
        style="text-decoration: none; color: rgb(255, 255, 255)"
        >회원가입</router-link
      >
      <a
        href="#"
        v-if="!isLogin"
        v-on:click="modalOpen"
        style="text-decoration: none; color: rgb(255, 255, 255)"
        >로그인</a
      >
      <div class="overlay" v-if="modalCheck"></div>
      <div class="modal-container" v-if="modalCheck">
        <form method="post">
          아이디:<input type="text" id="userId" /> 비밀번호:<input type="text" id="userPw" />
          <button v-on:click="modalOpen" class="btn">확인</button>

          <img src="../../assets/login/loginButtonKakao.jpg" alt="" @click="kakaoLogin" />
        </form>
      </div>

      <a href="#" v-if="isLogin" v-on:click="userLogout" style="text-decoration: none; color: red"
        >로그아웃</a
      >
    </div>
  </div>
</template>
<style scoped>
.nav {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  background-color: rgb(0, 0, 0);
  padding: 5px;
  width: 96vw;
  height: 7vh;
  box-shadow: 4px 4px 6px rgb(143, 143, 143);
}
.mainnav {
  display: inline-flex;
  flex-direction: row;
  justify-content: space-around;
  background-color: rgb(0, 0, 0);
  width: 55vw;
  height: 5vh;

  font-weight: bold;
  font-size: 25px;
  text-shadow: 2px 2px 6px gray;
  font-style: initial;
  color: inherit;
  text-decoration: none;
}
.loginnav {
  display: inline-flex;
  flex-direction: row;
  justify-content: space-around;
  background-color: rgb(0, 0, 0);
  width: 30vw;
  height: 5vh;
  border-radius: 5px;

  font-weight: bold;
  font-size: 25px;
  text-shadow: 2px 2px 6px gray;
  border-radius: 5px;
  font-style: initial;
}
.overlay {
  position: fixed;
  left: 0;
  top: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(223, 223, 223, 0.4);
  z-index: 10;
  box-shadow: 4px 4px 6px gray;
}
.modal-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 550px;
  background-color: silver;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
  z-index: 10;

  font-weight: bold;
  font-size: 15px;
  text-shadow: 2px 2px 6px gray;
  border-radius: 5px;
  font-style: initial;

  box-shadow: 4px 4px 6px gray;
}
.btn {
  background-color: rgb(207, 207, 207);
  border-radius: 5px;
  font-weight: bold;
  font-size: 15px;
  text-shadow: 2px 2px 6px gray;
  font-style: italic;
}
</style>
