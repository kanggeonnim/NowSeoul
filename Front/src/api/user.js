import { localAxios } from "@/util/http-commons";
import axios from "axios";

const local = localAxios();

async function userConfirm(param, success, fail) {
  await local.post(`/kakao/callback`, param).then(success).catch(fail);
}

async function userConfirmKakao(code, success, fail) {
  console.log("code is:" + code + "/");
  await local.post("/user/kakao/login", code).then(success).catch(fail);
  // await axios
  //   .post("http://localhost/user/kakao/login", new URLSearchParams({ code: code }))
  //   .then(success)
  //   .catch(fail);
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

export { userConfirm, findById, tokenRegeneration, logout, userConfirmKakao };
