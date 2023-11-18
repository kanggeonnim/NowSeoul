package com.ssafy.hotplace.model;

import lombok.Builder;
import lombok.Data;

@Data
public class WeatherDTO {
	private String roadTrafficList;	// 도로 구간별 혼잡도 리스트;
	private String temp;	// 기온
	private String sensibleTemp; // 체감온도 
	private String maxTemp; // 일 최고온도 
	private String minTemp; // 일 최저온도
	private String hunidity; // 습도
	private String windDir; // 풍향
	private String windSpd; // 풍속 
	private String precipitation; // 강수량
	private String pcpMsg; // 강수관련 메세지
	private String uvindex; // 자외선 지수
	private String uvMsg; // 자외선 메시지
	private String pm10Index; // 미세먼지 지표 
	private String pm10; // 미세먼지 농도 
	
	@Builder
	public WeatherDTO(String roadTrafficList, String temp, String sensibleTemp, String maxTemp, String minTemp,
			String hunidity, String windDir, String windSpd, String precipitation, String pcpMsg, String uvindex,
			String uvMsg, String pm10Index, String pm10) {
		super();
		this.roadTrafficList = roadTrafficList;
		this.temp = temp;
		this.sensibleTemp = sensibleTemp;
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
		this.hunidity = hunidity;
		this.windDir = windDir;
		this.windSpd = windSpd;
		this.precipitation = precipitation;
		this.pcpMsg = pcpMsg;
		this.uvindex = uvindex;
		this.uvMsg = uvMsg;
		this.pm10Index = pm10Index;
		this.pm10 = pm10;
	}
}
