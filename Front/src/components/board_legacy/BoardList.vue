<script setup>
import axios from "axios";
import { RouterLink } from "vue-router";
import { ref, watch } from "vue";
const fo = ref(3);
const fo1 = ref();
const Keyword = ref("");
const itemList = ref([]);
const itemLists = ref([]);
watch(Keyword, async () => {
  axios({
    method: "get",
    url: "https://9183a101-aaeb-46f6-9234-aa06122782ad.mock.pstmn.io/" + Keyword.value,
    responseType: "json",
  }).then(async (result) => {
    itemList.value = result.data["ma"];
    console.log("요기" + itemList.value[0].ap);
    for (let i = 0; i < itemList.value.length; i++) {
      itemLists.value.push(itemList.value[i].ap);
      console.log(itemLists.value[i] + "요기" + itemList.value[i].ap);
    }
  });
});
Keyword.value = "test";
</script>

<template>
  <div class="boardlist">
    <h1
      style="font-style: italic font-weight: bold;
  text-shadow: 2px 2px 6px gray;"
    >
      BoardList
    </h1>
    <table class="boardtable">
      <tr class="boarddata">
        <td>너와</td>
        <td>우리</td>
        <td>안의</td>
        <td>소리</td>
      </tr>
      <tr v-for="item in fo" :key="item" class="boarddata">
        <td>{{ itemLists[item] }}</td>
        <td>나의</td>
        <td>연결</td>
        <td>고리</td>
      </tr>
    </table>
    <router-link to="/board/write" class="btn"><button class="btn">글쓰기</button></router-link>
  </div>
</template>

<style scoped>
.boardlist {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 4px 4px 6px gray;
}
.boardtable {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 70vw;
  background-color: aquamarine;
  border: 1px solid red;
  border-radius: 5px;
  box-shadow: 4px 4px 6px gray;
}
.boarddata {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;

  width: 60vw;
  background-color: aquamarine;
  border: 1px solid red;
  border-radius: 5px;

  font-weight: bold;
  font-size: 15px;
  text-shadow: 2px 2px 6px gray;
  font-style: italic;
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
