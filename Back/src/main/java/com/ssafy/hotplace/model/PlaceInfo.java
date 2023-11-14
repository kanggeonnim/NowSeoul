package com.ssafy.hotplace.model;

import lombok.Data;

@Data
public class PlaceInfo {
	private String category;	// 분류
	private String areaNo;	// 장소 아이디
	private String areaCode;	// 장소 코드
	private String areaName;	// 장소 이름
}
