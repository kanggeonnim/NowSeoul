<script setup>
import axios from 'axios'
import { ref } from 'vue';
const socket = new WebSocket("ws://192.168.130.56:1234/ws/chat");

let list = ref([]);
axios.get("http://192.168.130.56:1234/chat")
.then((resp) => {
  list.value = resp.data;
  console.log(list);
});

let user;
function connect(id) {
  user = id;
  disconnect();
    var msg = {
    type:"ENTER",
    roomId: id,
    sender: "admin",
    message:""
  };
  socket.send(JSON.stringify(msg));
}

function disconnect() {
  document.getElementById("area").innerHTML = "";
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
</script>

<template>
  <div v-if="list.length > 0">
    <template v-for="item in list" :key="item">
      <!-- <a :href="`http://192.168.130.56:1234/chat/${item}`">{{ item }}</a>
      <br> -->
      <br><br>
      <label>{{ item }} <button @:click="connect(item)">접속</button></label>
    </template>
  </div>
  <div v-else>
    empty
  </div>
  <input type="text" name="message" id="txt" @keyup.enter="sendText">
    <button @click="sendText()" @keyup.enter="sendText()">전송</button>
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
  background-color: blue;
}
</style>