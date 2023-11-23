<script setup>
import axios from "axios";
import { ref } from "vue";
const wTitle1 = ref("");
const wDetail1 = ref("");
const bt = async () => {
  axios({
    method: "post",
    url: "http://192.168.130.55/post",
    params: {
      wTitle: wTitle1.value,
      wDetail: wDetail1.value,
    },
  });
};
//bt();
</script>

<template>
  <div class="write">
    <form>
      <p style="color: rgb(82, 25, 90)">제목</p>
      <input type="text" id="wTitle" class="wTitle" v-model="wTitle1" />

      <p style="color: rgb(82, 25, 90)">내용</p>
      <input type="text" id="wDetail" class="wDetail" v-model="wDetail1" />
      <div></div>
      <button class="btn" v-on:click="bt()">확인</button>
    </form>
    <RouterLink to="/board/list"></RouterLink>
  </div>
</template>

<style scoped>
.write {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 4px 4px 6px gray;

  font-weight: bold;
  font-size: 25px;
  text-shadow: 2px 2px 6px gray;
  border-radius: 5px;
  font-style: initial;
}
.wTitle {
  width: 60vw;
  height: 3vh;
  border: 1px solid blue;
  border-radius: 5px;
}
.wDetail {
  width: 60vw;
  height: 42vh;
  border: 1px solid blue;
  border-radius: 5px;
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
