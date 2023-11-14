package com.ssafy.hotplace.updater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
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

import com.ssafy.hotplace.model.LiveInfo;
import com.ssafy.hotplace.model.PlaceInfo;
import com.ssafy.hotplace.model.service.HotPlaceService;
import com.ssafy.hotplace.model.service.PlaceService;

@SpringBootApplication
@EnableScheduling
public class DataUpdater {

	private PlaceService placeService;
	private HotPlaceService hotplaceService;

	@Autowired
	public DataUpdater(PlaceService placeService, HotPlaceService hotplaceService) {
		super();
		this.placeService = placeService;
		this.hotplaceService = hotplaceService;
	}
	
	@Value("classpath:keys.json")
	Resource resourceFile;

	@Scheduled(cron = "0/10 * * * * *")
	private void run() throws Exception {
		String result = "";
		String line = new BufferedReader(new InputStreamReader(resourceFile.getInputStream())).readLine();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
		String key = (String) jsonObject.get("key");
		URL url;
		List<PlaceInfo> list = placeService.listArticle();
		List<LiveInfo> hotplaces = new LinkedList<LiveInfo>();
		
		for (PlaceInfo place : list) {
			url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/citydata_ppltn/1/5/" + place.getAreaCode());
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//			result = bf.readLine();
			
			jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(bf);
			JSONArray placeArray = (JSONArray) jsonObject.get("SeoulRtd.citydata_ppltn");
			jsonObject = (JSONObject) placeArray.get(0);
			
			LiveInfo hotplace =LiveInfo.builder()
					.areaName((String) jsonObject.get("AREA_NM"))
					.areaCode((String) jsonObject.get("AREA_CD"))
					.areaCongestLevel((String) jsonObject.get("AREA_CONGEST_LVL"))
					.areaCongestMessage((String) jsonObject.get("AREA_CONGEST_MSG"))
					.areaLiveMin((String) jsonObject.get("AREA_PPLTN_MIN"))
					.areaLiveMax((String) jsonObject.get("AREA_PPLTN_MAX"))
					.build();
				
			hotplaces.add(hotplace);
		}

		hotplaceService.insert(hotplaces);
	}
}
