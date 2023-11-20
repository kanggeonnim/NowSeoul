package com.ssafy.hotplace.model;

import lombok.Builder;
import lombok.Data;

@Data
public class HotplaceDTO {
	private String areaCode; // 핫스팟 코드명
	private String areaName; // 핫스팟 장소명
	private String areaCongestLevel; // 장소 혼잡도 지표
	private String areaCongestMessage; // 장소 혼잡도 지표 관련 메세지
	private String areaLiveMin; // 실시간 인구 지표 최소값
	private String areaLiveMax; // 실시간 인구 지표 최대값
	private double lat; // 위도
	private double lng; // 경도
	private String maleRate; // 남성 인구 비율
	private String femaleRate; // 여성 인구 비율

	// 연령대별 인구 비율
	private String ageRate0;
	private String ageRate10;
	private String ageRate20;
	private String ageRate30;
	private String ageRate40;
	private String ageRate50;
	private String ageRate60;
	private String ageRate70;

	@Builder
	public HotplaceDTO(String areaCode, String areaName, String areaCongestLevel, String areaCongestMessage,
			String areaLiveMin, String areaLiveMax, double lat, double lng, String maleRate, String femaleRate,
			String ageRate0, String ageRate10, String ageRate20, String ageRate30, String ageRate40, String ageRate50,
			String ageRate60, String ageRate70) {
		super();
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaCongestLevel = areaCongestLevel;
		this.areaCongestMessage = areaCongestMessage;
		this.areaLiveMin = areaLiveMin;
		this.areaLiveMax = areaLiveMax;
		this.lat = lat;
		this.lng = lng;
		this.maleRate = maleRate;
		this.femaleRate = femaleRate;
		this.ageRate0 = ageRate0;
		this.ageRate10 = ageRate10;
		this.ageRate20 = ageRate20;
		this.ageRate30 = ageRate30;
		this.ageRate40 = ageRate40;
		this.ageRate50 = ageRate50;
		this.ageRate60 = ageRate60;
		this.ageRate70 = ageRate70;
	}
	

}
