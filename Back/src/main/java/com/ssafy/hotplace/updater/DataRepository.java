package com.ssafy.hotplace.updater;

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
import com.ssafy.hotplace.model.service.HotPlaceService;
import com.ssafy.hotplace.model.service.PlaceService;

@SpringBootApplication
@EnableScheduling
public class DataRepository {

	private PlaceService placeService;
	private HotPlaceService hotplaceService;

	@Autowired
	public DataRepository(PlaceService placeService, HotPlaceService hotplaceService) {
		super();
		this.placeService = placeService;
		this.hotplaceService = hotplaceService;
	}

	@Value("classpath:keys.json")
	Resource resourceFile;

	@Scheduled(cron = "0 */5 * * * *")
	private void updateHotPlaces() throws Exception {
		String result = "";
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
					.areaLiveMax((String) jsonObject.get("AREA_PPLTN_MAX")).build();
			// hotplaces.add(hotplace);
		}

		// System.out.println(hotplaces);
		// hotplaceService.insert(hotplaces);
		hotplaceService.update(hotplaces);
		System.out.println("hotplaces update success");
	}

	@Scheduled(cron = "0 */5 * * * *")
	private void updateRestaurants() throws Exception {
		String result = "";
		String line = new BufferedReader(new InputStreamReader(resourceFile.getInputStream())).readLine();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
		String key = (String) jsonObject.get("key");
		URL url;
		List<PlaceDTO> list = placeService.listArticle();
		List<HotplaceDTO> hotplaces = new ArrayList<HotplaceDTO>();

		for (int i = 1;; i += 1000) {
			url = new URL(
					"http://openapi.seoul.go.kr:8088/" + key + "/json/citydata_ppltn/" + i + "/" + i + 1000 + "/");
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
					.areaLiveMax((String) jsonObject.get("AREA_PPLTN_MAX")).build();
		}

	}
}
