<script setup>
import MapView from "../../views/MapView.vue";
import axios from "axios";
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { RouterView, useRouter, RouterLink } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";
import { KAKAO_AUTH_URL } from "@/api/kakao/KakaoAuth";

const router = useRouter();
const memberStore = useMemberStore();
const modalCheck = ref(false);
const modalOpen = () => {
  modalCheck.value = !modalCheck.value;
};
const { isLogin } = storeToRefs(memberStore);
const { userLoginKakao, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
  userId: "",
  userPwd: "",
});

const login = async () => {
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");
  if (isLogin) {
    getUserInfo(token);
    changeMenuState();
  }
  router.push("/");
};
const kakaoLogin = () => {
  window.location.href = KAKAO_AUTH_URL;
};

// onMounted(() => {
//   isMember();
// });

// const isMember = () => {
//   //URL의 param중 code값을 내려받기
//   let code = new URL(window.location.href).searchParams.get("code");
//   //fordata로 code 감싸기
//   const codeDataForm = new FormData();
//   codeDataForm.append("code", code);

//   //본인 서버 해당 URL로 code 보내기
//   axios
//     .post(`http://192.168.130.54/kakao/callback`, codeDataForm, {
//       headers: {
//         "content-type": "application/x-www-form-urlencoded;charset=utf-8",
//       },
//     })
//     .then((response) => {
//       //response로 accessToken 및 refresh, 사용자 정보
//       let ACCESS_TOKEN = response.data.access_token;

//       //엑세스 토큰 글로벌 저장 getAccessToken()으로 사용가능
//       window.Kakao.Auth.setAccessToken(ACCESS_TOKEN);

//       //이후 해당 사이트 로그인 진행(스토리지 저장)
//     })
//     .catch((error) => {
//       console.log(error.response.data);
//     });
// };
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

      <a href="#" v-if="isLogin" v-on:click="modalOpen" style="text-decoration: none; color: red"
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
