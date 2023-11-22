<script setup>
import { defineProps, ref, watch, onMounted, toRefs } from "vue";

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    // initMap();
  } else {
    const script = document.createElement("script");
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=74d60f58abf23ff7914f48f1c9647bb2&libraries=services,clusterer";
    /*global kakao*/
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});
const initMap = () => {
  var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

  var options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.5566293300794875, 126.972904389242675), //지도의 중심좌표.
    level: 3, //지도의 레벨(확대, 축소 정도)
  };

  map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
};

const dodo = () => {
  console.log(
    "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
  );
};
const props = defineProps(["mapDetailInfo"]);
const { mapDetailInfo } = toRefs(props);
console.log("detail receive1", mapDetailInfo.value.item);

// const detail = ref(null);
// console.log("detail", detail.get(''));
// console.log("detail", detail.innerHTML);
// console.log("detail", detail.value);
// console.log("detail", detail.innerHTML);
// // console.log("detail", detail.value.innerHTML);
// console.log("detail", detail.text);
// console.log("detail", detail.innerHTML);
// console.log("detail", detail.value.innerHTML);

const detail = (e) => {
  console.log("is here", e.innerHTML);

  mapDetailInfo.value.infowindow = new kakao.maps.InfoWindow({
    content: e.innerHTML,
    removable: true,
  });
  mapDetailInfo.value.infowindow.open(
    mapDetailInfo.value.map,
    mapDetailInfo.value.marker
  );
};
const hidden = ref(false);
</script>

<template>
  <div v-show="hidden">
    <div :ref="detail">
      <div>
        <img
          class="bao"
          style="-webkit-user-drag: none"
          :src="`https://data.seoul.go.kr/SeoulRtd/images/hotspot/${mapDetailInfo.item.areaName}.jpg`"
        />
        <div>
          {{ mapDetailInfo.item.areaName }}
          <!-- ${itemList.value[i].areaCongestLevel}
      ${itemList.value[i].areaCongestMessage} ${itemList.value[i].areaLiveMin}
      ${itemList.value[i].areaLiveMax}
      ${itemList.value[i].maleRate}${itemList.value[i].femaleRate}
      ${itemList.value[i].ageRate0} ${itemList.value[i].ageRate10}
      ${itemList.value[i].ageRate20} ${itemList.value[i].ageRate30}
      ${itemList.value[i].ageRate40} ${itemList.value[i].ageRate50}
      ${itemList.value[i].ageRate60} ${itemList.value[i].ageRate70} -->
        </div>
        <div>
          <button id="bao12" v-on:click="dodo">상세보기</button>
        </div>
        <button id="">추천</button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
