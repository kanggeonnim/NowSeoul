package com.ssafy.hotplace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@Value("classpath:keys.json")
	Resource resourceFile;

	@PostMapping("/api")
	public String load_save(@RequestParam("date") String date, Model model) throws IOException, ParseException {
		String result = "";
		String line = new BufferedReader(new InputStreamReader(resourceFile.getInputStream())).readLine();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
		String key = (String) jsonObject.get("key");

		try {
			String requestDate = date;
			URL url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/citydata_ppltn/1/5/" + requestDate);
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();

			jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject CardSubwayStatsNew = (JSONObject) jsonObject.get("CardSubwayStatsNew");
			Long totalCount = (Long) CardSubwayStatsNew.get("list_total_count");

			JSONObject subResult = (JSONObject) CardSubwayStatsNew.get("RESULT");
			JSONArray infoArr = (JSONArray) CardSubwayStatsNew.get("row");

//	           for(int i=0;i<infoArr.size();i++){
//	               JSONObject tmp = (JSONObject)infoArr.get(i);
//	               PlaceInfo infoObj=new PlaceInfo(i+(long)1, (String)tmp.get("USE_DT"),(String)tmp.get("LINE_NUM"),(String)tmp.get("SUB_STA_NM"),
//	                       (double)tmp.get("RIDE_PASGR_NUM"), (double)tmp.get("ALIGHT_PASGR_NUM"),(String)tmp.get("WORK_DT"));
//	           }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/findname";

	}

	@GetMapping("/test")
	public void test() throws IOException, ParseException {
		String line = new BufferedReader(new InputStreamReader(resourceFile.getInputStream())).readLine();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
		String key = (String) jsonObject.get("key");
		System.out.println(key);
	}

	@PostMapping("/post")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String wTitle, @RequestParam String wDetail) {

		System.out.println(wTitle + "" + wDetail);
		return null;
	}
}

///http://openapi.seoul.go.kr:8088/446662464967756e34354644636b78/json/citydata_ppltn/1/5/%EA%B4%91%ED%99%94%EB%AC%B8%C2%B7%EB%8D%95%EC%88%98%EA%B6%81
