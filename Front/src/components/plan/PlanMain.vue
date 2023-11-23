<script setup>
import { ref, onMounted, reactive } from 'vue';
import * as THREE from 'three';
import { GLTFLoader } from "three/examples/jsm/loaders/GLTFLoader";
// https://sketchfab.com/3d-models/azir-league-of-legends-character-1bcad9785c344aab93e776f9e9e6396d

const isMounted = reactive({ value: false });
const container = ref();

const scene = new THREE.Scene();
scene.background = new THREE.Color('white');

const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 1000);
camera.position.z = 20;
camera.position.y = 8;

const renderer = new THREE.WebGLRenderer();
renderer.setSize(800, 1200);
renderer.antialias = true;
renderer.outputColorSpace = THREE.SRGBColorSpace;
// renderer.outputEncoding = THREE.sRGBEncoding;

const loader = new GLTFLoader();
let azir;

// mix
let mixer;
// /mix

function loadModelWithAnimation(animationIndex) {
  loader.load('/models/fullzir/source/AzirComplete.glb',
    (gltf, geometry, mat) => {
      azir = gltf.scene;
      azir.scale.set(0.05, 0.05, 0.05);

      // const textureLoader = new THREE.TextureLoader();
      // const texture = textureLoader.load('/models/azir/textures/Azir_Mat_baseColor.png');
      // const azirMaterial = new THREE.MeshStandardMaterial({ map: texture });

      // azir.traverse((child) => {
      //   if (child instanceof THREE.Mesh) {
      //       console.log("azir : \n");
      //       console.log(child.name, child);
      //       child.material = azirMaterial;
      //     }
      //   }
      // )

      if (gltf.animations && gltf.animations.length > 0) {
        mixer = new THREE.AnimationMixer(azir);

        const animationToPlay = typeof animationIndex === 'azir_attack1.anm'
          ? gltf.animations[animationIndex]
          : gltf.animations.find(anim => anim.name === animationIndex);

        if (animationToPlay) {
          mixer.clipAction(animationToPlay).play();
        }
        else {
          console.warn('animation not found');
        }
      }

      if (isMounted.value) {
        scene.add(azir);
        console.log(azir);

        animate();
      }
    }
  );
}

function animate() {
  requestAnimationFrame(animate)
  if (mixer) {
    mixer.update(0.01);
  }
  renderer.render(scene, camera);
}

onMounted(() => {
  isMounted.value = true;
  container.value.appendChild(renderer.domElement);
  // 원하는 모션 선택해서 넣으면 나옴
  loadModelWithAnimation('azir_attack1.anm');
});
</script>

<template>
  <div ref="container"></div>
</template>

<style scoped>

</style>

<!-- <script setup>
import axios from "axios";
import { storeToRefs } from "pinia";
import { useCounterStore } from "@/stores/counter";
import { ref, watch, onMounted } from "vue";
const Keyword = ref("");
const Keyword1 = ref("");
const Keyword2 = ref();
const item = ref("");
const itemList = ref([]);
const addsList = ref([]);
var map = ref("");
const store = useCounterStore();
const { name, count, name1, addList } = storeToRefs(store);
const { increment } = store;
const go = () => {
  store.count++;
  addList.value.push(Keyword1.value);
  console.log("되냐" + store.count);
  console.log("되냐" + store.addList);
};
const lolo = () => {
  console.log("되냐@" + addList.value[Keyword2.value]);
  console.log("되냐@%" + store.addList.length);
};
console.log(store);
watch(Keyword, async () => {
  axios({
    method: "get",
    url:
      "https://9183a101-aaeb-46f6-9234-aa06122782ad.mock.pstmn.io/" +
      Keyword.value,
    responseType: "json",
  }).then(async (result) => {
    itemList.value = result.data["ma"];
    console.log("요기" + itemList.value[0].ap);
  });
});
const markerXY = ref([
  {
    markerX: "",
    markerY: "",
  },
]);
onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=74d60f58abf23ff7914f48f1c9647bb2&libraries=services,clusterer";
    /*golbal kakao*/
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

const initMap = () => {
  var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

  var options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.5532795449, 126.968580367), //지도의 중심좌표.
    level: 3, //지도의 레벨(확대, 축소 정도)
  };

  map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
  // 마커가 표시될 위치입니다
  var markerPosition = new kakao.maps.LatLng(37.5532795449, 126.968580367);

  // 마커를 생성합니다

  var marker = new kakao.maps.Marker({
    position: markerPosition,
  });
  const cosel = ref(false);
  watch(
    () => cosel.value,
    () => {
      if (cosel.value) {
        console.log(cosel.value);
        customOverlay.setMap(map);
      } else if (!console.value) {
        console.log("뭐해");
        customOverlay.setMap(null);
      }
    }
  );
  marker.setMap(map);
  // 마커에 click 이벤트를 등록합니다
  // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다

  const iwContent = `<div>인포</div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
  const iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

  // 인포윈도우를 생성합니다
  var infowindow = new kakao.maps.InfoWindow({
    content: iwContent,
    removable: iwRemoveable,
  });
  kakao.maps.event.addListener(marker, "click", function () {
    infowindow.open(map, marker);
    // 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
    // 마커의 이미지를 클릭 이미지로 변경합니다
    //if (!selectedMarker || selectedMarker !== marker) {
    // 클릭된 마커 객체가 null이 아니면
    // 클릭된 마커의 이미지를 기본 이미지로 변경하고
    //!!selectedMarker && selectedMarker.setImage(selectedMarker.normalImage);
    // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
    //marker.setImage(clickImage);
    //}
    markerXY.markerX = markerPosition.getLat();
    markerXY.markerY = markerPosition.getLng();
    console.log(markerXY);

    // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
    //selectedMarker = marker;
  });
  infowindow.setZIndex(90);
  marker.setZIndex(90);
  marker.setMap(map);
  var linePath = [
    new kakao.maps.LatLng(37.5232795449, 126.938580367),
    new kakao.maps.LatLng(37.5432795449, 126.958580367),
    new kakao.maps.LatLng(37.5532795449, 126.968580367),
    37.5532795449,
    126.968580367,
  ];

  // 지도에 표시할 선을 생성합니다
  var polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: "#FFAE00", // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "solid", // 선의 스타일입니다
  });
  async function getCarDirection() {
    const REST_API_KEY = "2aae8d5b262226bb06c0e6e7989c687d";
    // 호출방식의 URL을 입력합니다.
    const url = "https://apis-navi.kakaomobility.com/v1/directions";

    // 출발지(origin), 목적지(destination)의 좌표를 문자열로 변환합니다.
    const origin = `${pointObj.startPoint.lng},${pointObj.startPoint.lat}`;
    const destination = `${pointObj.endPoint.lng},${pointObj.endPoint.lat}`;

    // 요청 헤더를 추가합니다.
    const headers = {
      Authorization: `KakaoAK ${REST_API_KEY}`,
      "Content-Type": "application/json",
    };

    // 표3의 요청 파라미터에 필수값을 적어줍니다.
    const queryParams = new URLSearchParams({
      origin: origin,
      destination: destination,
    });

    const requestUrl = `${url}?${queryParams}`; // 파라미터까지 포함된 전체 URL

    try {
      const response = await fetch(requestUrl, {
        method: "GET",
        headers: headers,
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();

      console.log(data);
    } catch (error) {
      console.error("Error:", error);
    }
    data.routes[0].sections[0].roads.forEach((router) => {
      router.vertexes.forEach((vertex, index) => {
        // x,y 좌표가 우르르 들어옵니다. 그래서 인덱스가 짝수일 때만 linePath에 넣어봅시다.
        // 저도 실수한 것인데 lat이 y이고 lng이 x입니다.
        if (index % 2 === 0) {
          linePath.push(
            new kakao.maps.LatLng(
              router.vertexes[index + 1],
              router.vertexes[index]
            )
          );
        }
      });
    });

    polyline.setMap(map);
  }
};
</script>

<template>
  <div><input type="text" v-model="Keyword1" v-on:keydown.enter="go" /></div>
  <div><input type="text" v-model="Keyword2" v-on:keydown.enter="lolo" /></div>
  <div class="mainmap" id="map">map</div>
</template>

<style scoped>
.mainmap {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 96vw;
  height: 50vh;
  border: 1px solid red;
  border-radius: 5px;
}
</style> -->
