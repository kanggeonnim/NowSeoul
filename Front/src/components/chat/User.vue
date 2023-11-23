<script setup>
import axios from 'axios'
import { ref } from 'vue';
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
// axios.post("http://192.168.130.56:1234/chat?name=qwer");

//  postman - http://192.168.130.56:1234/chat?name=tempo2
// post + roomId = "asd" 고정
const socket = new WebSocket("ws://192.168.130.56:1234/ws/chat");

let user;
let connect = async () => {
  user = admin.id;
  console.log(user);
  axios.post("http://192.168.130.56:1234/chat?name="+user)
  .then((message) => {
    console.log(message);
  })
}

function change() {
  user = admin.id;
  var msg = {
    type:"ENTER",
    roomId: user,
    sender: user,
    message:""
  };
  socket.send(JSON.stringify(msg));
}

function disconnect() {
  user = document.getElementById("UserId").value;
  document.getElementById("area").innerHTML = "";
  axios.delete("http://192.168.130.56:1234/chat?name=" + user);
}

socket.onmessage = function (e) {

  var list = document.getElementById("area").innerHTML;
  var json = JSON.parse(e.data);
  console.log(json);

  if (json.type === "ENTER") {
    list = list += ("<p class='enter'>" + json.message + "</p>");
  }
  else if (json.sender === user) list = list += ("<p class='my'>" + json.message + "</p>");
  else list = list += ("<p class='other'> <strong>" + json.sender + " : </strong>" + json.message + "</p>");

  document.getElementById("area").innerHTML = list;
}

function sendText() {
  var txt = document.getElementById("txt").value;

  var msg = {
    type:"TALK",
    roomId: user,
    sender: user,
    message: txt
  }

  if (txt !== "") socket.send(JSON.stringify(msg));
  document.getElementById("txt").value = "";
}

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const admin = ref({
  id: userInfo.value.id,
  name: userInfo.value.name,
});
async function init() {
  // user = admin.id;
  await connect();
  await change();
}
init();
</script>

<template>
  <div>
    <!-- <form action="http://192.168.130.56:1234/chat" method="post" id="hiddenForm">
      <input type="hidden" id="userName">
      <input type="hidden" id="roomId">
    </form> -->
    <br>
    <!-- <input type="text" id="UserId" @keyup.enter="change">
    <br>
    <button @:click="connect()">생성</button> |
    <button @:click="change()">접속</button> |
    <button @:click="disconnect()">종료</button>
    <br> -->
    <br>
    <!-- <button @:click="connect()">연결</button>
    <button @:click="disconnect()">끊기</button>
    <br>
    <br> -->
    <input type="text" name="message" id="txt" @keyup.enter="sendText">
    <button @click="sendText()" @keyup.enter="sendText()">전송</button>
  </div>
  <div id="area"></div>
</template>

<script>
function scrollToBottom() {
  var chatContainer = document.getElementById('area');
  chatContainer.scrollTop = chatContainer.scrollHeight;
}

// 예제: 0.5초마다 한 번씩 스크롤을 아래로 내립니다.
setInterval(scrollToBottom, 50);
</script>

<style scoped>
#area {
  border: 1px solid black;
  width: 80vw;
  height: 80vh;
  overflow-y: auto;
}

.enter {
  text-align: center;
}

.my {
  text-align: right;
}

.other {
  text-align: left;
}

body {
  background-color: red;
}
</style>