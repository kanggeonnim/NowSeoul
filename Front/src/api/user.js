import { localAxios } from "@/util/http-commons";
import axios from "axios";

const local = localAxios();

async function userConfirm(param, success, fail) {
  await local.post(`/kakao/callback`, param).then(success).catch(fail);
}

async function userConfirmKakao(code, sucess, fail) {
  // console.log(code);
  await local.post("http://192.168.130.54/user/kakao/login", code).then(sucess).catch(fail);
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`http://192.168.130.54/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`http://192.168.130.54/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`http://192.168.130.54/user/logout/${userid}`).then(success).catch(fail);
}

export { userConfirm, findById, tokenRegeneration, logout, userConfirmKakao };
