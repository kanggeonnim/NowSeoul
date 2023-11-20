package com.ssafy.hotplace.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ssafy.hotplace.model.HotplaceDTO;
import com.ssafy.hotplace.model.PlaceDTO;
import com.ssafy.hotplace.model.RestaurantDTO;
import com.ssafy.hotplace.model.service.HotPlaceService;
import com.ssafy.hotplace.model.service.NaverCrawlingService;
import com.ssafy.hotplace.model.service.PlaceService;

@SpringBootApplication
@EnableScheduling
public class DataRepository {

	private PlaceService placeService;
	private HotPlaceService hotplaceService;
	private NaverCrawlingService naverService;

	@Autowired
	public DataRepository(PlaceService placeService, HotPlaceService hotplaceService,
			NaverCrawlingService naverService) {
		super();
		this.placeService = placeService;
		this.hotplaceService = hotplaceService;
		this.naverService = naverService;
	}

	@Value("classpath:keys.json")
	Resource resourceFile;

	@Scheduled(cron = "0 */5 * * * *")	// 5분마다
//	@Scheduled(cron = "0/10 * * * * *")	// 10초마다
	private void updateHotPlaces() throws Exception {
		String line = new BufferedReader(new InputStreamReader(resourceFile.getInputStream())).readLine();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
		String key = (String) jsonObject.get("key");
		URL url;
		List<PlaceDTO> list = placeService.listArticle();
		List<HotplaceDTO> hotplaces = new ArrayList<HotplaceDTO>();

		for (PlaceDTO place : list) {
			url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/citydata_ppltn/1/5/" + place.getAreaCode());
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(bf);
			JSONArray placeArray = (JSONArray) jsonObject.get("SeoulRtd.citydata_ppltn");
			jsonObject = (JSONObject) placeArray.get(0);
			HotplaceDTO hotplace = HotplaceDTO.builder().areaName((String) jsonObject.get("AREA_NM"))
					.areaCode((String) jsonObject.get("AREA_CD"))
					.areaCongestLevel((String) jsonObject.get("AREA_CONGEST_LVL"))
					.areaCongestMessage((String) jsonObject.get("AREA_CONGEST_MSG"))
					.areaLiveMin((String) jsonObject.get("AREA_PPLTN_MIN"))
					.areaLiveMax((String) jsonObject.get("AREA_PPLTN_MAX"))
					.maleRate((String) jsonObject.get("MALE_PPLTN_RATE"))
					.femaleRate((String) jsonObject.get("FEMALE_PPLTN_RATE"))
					.ageRate0((String) jsonObject.get("PPLTN_RATE_0"))
					.ageRate10((String) jsonObject.get("PPLTN_RATE_10"))
					.ageRate20((String) jsonObject.get("PPLTN_RATE_20"))
					.ageRate30((String) jsonObject.get("PPLTN_RATE_30"))
					.ageRate40((String) jsonObject.get("PPLTN_RATE_40"))
					.ageRate50((String) jsonObject.get("PPLTN_RATE_50"))
					.ageRate60((String) jsonObject.get("PPLTN_RATE_60"))
					.ageRate70((String) jsonObject.get("PPLTN_RATE_70"))
					.build();
			hotplaces.add(hotplace);
		}

//		System.out.println(hotplaces);
//		hotplaceService.insert(hotplaces);
		
		hotplaceService.update(hotplaces);

		System.out.println("hotplaces update success");
	}

//    @Scheduled(cron = "0/10 * * * * *")
//    private void updateRestaurants() throws Exception {
//        String line = new BufferedReader(new InputStreamReader(resourceFile.getInputStream())).readLine();
//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
//        String key = (String) jsonObject.get("key");
//        URL url;
//        
//        naverService.getNaverData("서울 마곡동 델리케밥");
//        System.out.println("NaverService called");
//        
//        for (int i = 1;i<2000; i += 1000) {
//            url = new URL(
//                    "http://openapi.seoul.go.kr:8088/" + key + "/json/LOCALDATA_072404/" + i + "/" + (i + 9) + "/");
//            BufferedReader bf;
////            System.out.println(url);
//            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//            jsonParser = new JSONParser();
//            jsonObject = (JSONObject) jsonParser.parse(bf);
//            JSONObject listData = (JSONObject) jsonObject.get("LOCALDATA_072404");
//            JSONObject apiResult = (JSONObject) listData.get("RESULT");
//            String resultCode = (String) apiResult.get("CODE");
//            
//            // 응답이 정상적으로 받아지지 않으면 종료.
//            if(!resultCode.equals("INFO-000"))    break;
//            
////            System.out.println(resultCode);
//            
//            JSONArray restaurantArray =  (JSONArray) listData.get("row");
//            
//            
//            // 1000개 단위로 받아온 리스트를 DB에 저장.
//            List<RestaurantDTO> restaurantList = new ArrayList<RestaurantDTO>();
//            
//            for(Object o: restaurantArray) {
//            	JSONObject r = (JSONObject) o;
//            	RestaurantDTO restaurant = RestaurantDTO.builder()
//            			.manageNo((String) r.get("MGTNO"))
//            			.detaileStateName((String) r.get("DTLSTATENM"))
//            			.tel((String) r.get("SITETEL"))
//            			.postNo((String) r.get("SITEPOSTNO"))
//            			.oldAddr((String) r.get("SITEWHLADDR"))
//            			.streetAddr((String) r.get("RDNWHLADDR"))
//            			.businessplaceName((String) r.get("BPLCNM"))
//            			.uptaeName((String) r.get("UPTAENM"))
//            			.x((String) r.get("X"))
//            			.y((String) r.get("Y"))
//            			.menu((String) r.get("MENU"))
//            			.openingHours((String) r.get("OPENING_HOURS"))
//            			.build();
////                System.out.println(restaurant);
////                System.out.println(r);
//                
//                restaurantList.add(restaurant);
//            }
//
//        }
//
//    }
}
