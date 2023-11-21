<script setup>
import axios from "axios";
import { ref, onMounted, watch } from "vue";

var map;

const keyword = ref("서울역");
const props = defineProps({});
const count = ref(0);
const itemList = ref([]);
const itemLists = ref([]);
const markers1 = ref([]);
const lati = ref([]);
const lngi = ref([]);
const htitle = ref([""]);
const pngtitle = ref([""]);
var latlangs = ref([]);
const bt = async () => {
  axios({
    method: "get",
    url: "http://192.168.130.55/live",
    responseType: "json",
  }).then(async (result) => {
    itemList.value = result.data;
    for (let i = 0; i < itemList.value.length; i++) {
      itemLists.value.push(itemList.value[i]);
      lati.value[i] = itemLists.value[i].lat;
      lngi.value[i] = itemLists.value[i].lng;
      htitle.value[i] = itemLists.value[i].areaName;
      pngtitle.value[i] = itemLists.value[i].areaCongestLevel;
    }
  });
};
bt();
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
    center: new kakao.maps.LatLng(37.5566293300794875, 126.972904389242675), //지도의 중심좌표.
    level: 3, //지도의 레벨(확대, 축소 정도)
  };

  map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

  const eventOpen = (marker, infowindow) => {
    infowindow.open(map, marker);
    var btn = document.getElementById("kk");
    const conselon = () => {
      customOverlay.setMap(map);
      map.setZoomable(false);

      btn.onclick = conselon;
    };
  };
  const eventClose = (infowindow) => {
    return () => {
      infowindow.close(infowindow);
    };
  };
  for (var i = 0; i < lati.value.length; i++) {
    var latlang = new kakao.maps.LatLng(lati.value[i], lngi.value[i]);
    latlangs.value[i] = latlang;
    // 마커 이미지의 이미지 크기 입니다${pngtitle.value[i]}
    var imageSrc = `../src/assets/${pngtitle.value[i]}.png`;
    var imageSize = new kakao.maps.Size(35, 35);
    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    var marker0 = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: latlangs.value[i], // 마커를 표시할 위치
      title: htitle.value[i], // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });
    const iwContent = `<div> <button id="kk" >음식점${count.value} </button></div><div><button id="" >장소 </button></div><div><button id="" >상세보기 </button></div><div><button id="" >추천</button></div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    const iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
    var content = `<div class ="label"><span class="left"></span><span class="center"> <img
        class="bao"style="-webkit-user-drag: none;"
        src="https://data.seoul.go.kr/SeoulRtd/images/hotspot/${htitle.value[1]}.jpg"

        /></span><span class="right"></span></div>`;
    var customOverlay = new kakao.maps.CustomOverlay({
      position: latlangs.value[i],
      content: content,
    });
    // 인포윈도우를 생성합니다
    var infowindow1 = new kakao.maps.InfoWindow({
      content: iwContent,
      removable: iwRemoveable,
    });
    console.log(latlangs.value[i]);
    kakao.maps.event.addListener(
      marker0,
      "mouseover",
      eventOpen(marker0, infowindow1)
    );
    kakao.maps.event.addListener(marker0, "mouseout", eventClose(infowindow1));
    console.log("askn");
  }
  //marker0.setMap(map);
  //"https://data.seoul.go.kr/SeoulRtd/heatmap_api?hotspotNm=서울역&amp;baseDate=20231120&amp;timeCd=1100&amp;minX=126.9685803678149&amp;minY=37.55327954490795&amp;maxX=126.97722841067045&amp;maxY=37.559979115251025&amp;width=768&amp;height=777"

  // 마커를 생성합니다

  //######################################################################################################
  watch(
    () => keyword.value,
    () => {
      if (keyword.value) {
        console.log("바뀌냐" + keyword.value);
        searchPlaces;
      }
    }
  );
  // 마커를 담을 배열입니다
  const markers = ref([]);
  // 키워드 검색을 요청하는 함수입니다
  const searchPlaces = () => {
    console.log(keyword.value);
    if (!keyword.value.replace(/^\s+|\s+$/g, "")) {
      alert("키워드를 입력해주세요!");
      return false;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch(keyword.value, placesSearchCB);
  };
  // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
  const placesSearchCB = (data, status, pagination) => {
    if (status === kakao.maps.services.Status.OK) {
      // 정상적으로 검색이 완료됐으면
      // 검색 목록과 마커를 표출합니다
      displayPlaces(data);

      // 페이지 번호를 표출합니다
      displayPagination(pagination);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      alert("검색 결과가 존재하지 않습니다.");
      return;
    } else if (status === kakao.maps.services.Status.ERROR) {
      alert("검색 결과 중 오류가 발생했습니다.");
      return;
    }
  };

  // 검색 결과 목록과 마커를 표출하는 함수입니다
  const displayPlaces = (places) => {
    var listEl = document.getElementById("placesList"),
      menuEl = document.getElementById("menu_wrap"),
      fragment = document.createDocumentFragment(),
      bounds = new kakao.maps.LatLngBounds(),
      listStr = "";

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();

    for (var i = 0; i < places.length; i++) {
      // 마커를 생성하고 지도에 표시합니다
      var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
        marker = addMarker(placePosition, i),
        itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      bounds.extend(placePosition);

      // 마커와 검색결과 항목에 mouseover 했을때
      // 해당 장소에 인포윈도우에 장소명을 표시합니다
      // mouseout 했을 때는 인포윈도우를 닫습니다
      (function (marker, title) {
        kakao.maps.event.addListener(marker, "mouseover", function () {
          displayInfowindow(marker, title);
          console.log(i);
        });

        kakao.maps.event.addListener(marker, "mouseout", function () {
          infowindow.close();
        });

        itemEl.onmouseover = function () {
          displayInfowindow(marker, title);
        };

        itemEl.onmouseout = function () {
          infowindow.close();
        };
      })(marker, places[i].place_name);

      fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
  };

  // 검색결과 항목을 Element로 반환하는 함수입니다
  const getListItem = (index, places) => {
    var el = document.createElement("li"),
      itemStr =
        '<span class="markerbg marker_' +
        (index + 1) +
        '"></span>' +
        '<div class="info">' +
        "   <h5>" +
        places.place_name +
        "</h5>";

    if (places.road_address_name) {
      itemStr +=
        "    <span>" +
        places.road_address_name +
        "</span>" +
        '   <span class="jibun gray">' +
        places.address_name +
        "</span>";
    } else {
      itemStr += "    <span>" + places.address_name + "</span>";
    }

    itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

    el.innerHTML = itemStr;
    el.className = "item";

    return el;
  };

  // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
  const addMarker = (position, idx, title) => {
    var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
      imgOptions = {
        spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.value.push(marker); // 배열에 생성된 마커를 추가합니다

    return marker;
  };

  // 지도 위에 표시되고 있는 마커를 모두 제거합니다
  const removeMarker = () => {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers.value = [];
  };

  // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
  const displayPagination = (pagination) => {
    var paginationEl = document.getElementById("pagination"),
      fragment = document.createDocumentFragment(),
      i;

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
      paginationEl.removeChild(paginationEl.lastChild);
    }

    for (i = 1; i <= pagination.last; i++) {
      var el = document.createElement("a");
      el.href = "#";
      el.innerHTML = i;

      if (i === pagination.current) {
        el.className = "on";
      } else {
        el.onclick = (function (i) {
          return function () {
            pagination.gotoPage(i);
          };
        })(i);
      }

      fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
  };
  // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
  // 인포윈도우에 장소명을 표시합니다
  const displayInfowindow = (marker, title) => {
    var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

    infowindow.setContent(content);
    infowindow.open(map, marker);
  };

  // 검색결과 목록의 자식 Element를 제거하는 함수입니다
  const removeAllChildNods = (el) => {
    while (el.hasChildNodes()) {
      el.removeChild(el.lastChild);
    }
  };
  // 장소 검색 객체를 생성합니다
  var ps = new kakao.maps.services.Places();

  // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
  var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  // 키워드로 장소를 검색합니다
  searchPlaces();
};
</script>

<template>
  <div class="map_wrap" style="position: ">
    <div
      class="mainmap"
      id="map"
      style="position: relative; overflow: hidden"
    ></div>

    <div id="menu_wrap" class="bg_white">
      <div class="option">
        <div>
          키워드 :
          <input
            type="text"
            v-model="keyword"
            size="15"
            v-on:keydown.enter="initMap()"
          />
          <button v-on:click="initMap()">검색하기</button>
        </div>
      </div>
      <hr />
      <ul id="placesList"></ul>
      <div id="pagination"></div>
    </div>
  </div>
</template>

<style>
.mainmap {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 96vw;
  height: 80vh;
  border: 1px solid red;
  border-radius: 5px;
}
.bao {
  opacity: 0.5;
  transform: scale();
  width: 50vw;
  height: 50vh;
  z-index: 2;
  pointer-events: none;
}
/* ################################################################################################################ */

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
</style>
