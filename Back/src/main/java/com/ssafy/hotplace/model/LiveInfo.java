package com.ssafy.hotplace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class LiveInfo {
	private String areaName;	// 핫스팟 장소명
	private String areaCode;	// 핫스팟 코드명
	private String areaCongestLevel;	// 장소 혼잡도 지표	
	private String areaCongestMessage;	// 장소 혼잡도 지표 관련 메세지
	private String areaLiveMin;	// 실시간 인구 지표 최소값
	private String areaLiveMax;	// 실시간 인구 지표 최대값
	
	@Builder
	public LiveInfo(String areaName, String areaCode, String areaCongestLevel, String areaCongestMessage,
			String areaLiveMin, String areaLiveMax) {
		super();
		this.areaName = areaName;
		this.areaCode = areaCode;
		this.areaCongestLevel = areaCongestLevel;
		this.areaCongestMessage = areaCongestMessage;
		this.areaLiveMin = areaLiveMin;
		this.areaLiveMax = areaLiveMax;
	}
	
	
}
