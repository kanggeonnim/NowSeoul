<script setup>
import axios from "axios";
import { ref, watch } from "vue";
const Keyword = ref("");
const item = ref("");
const itemList = ref([]);
watch(Keyword, async () => {
  axios({
    method: "get",
    url: "https://9183a101-aaeb-46f6-9234-aa06122782ad.mock.pstmn.io/" + Keyword.value,
    responseType: "json",
  }).then(async (result) => {
    itemList.value = result.data["ma"];
    console.log("요기" + itemList.value[0].ap);
  });
});
</script>

<template>
  <div>fkbndlnlnklbnlnblknalnbfnlkn</div>
  <div>검색어 {{ Keyword }}</div>
  <table>
    <input type="text" v-model="Keyword" />
  </table>
</template>

<style scoped></style>
