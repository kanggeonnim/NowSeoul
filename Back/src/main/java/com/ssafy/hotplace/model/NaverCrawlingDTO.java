package com.ssafy.hotplace.model;

import lombok.Builder;
import lombok.Data;

@Data
public class NaverCrawlingDTO {
	private String tel;
	private String menu;
	private String openingHours;
	private String image;
	
	@Builder
	public NaverCrawlingDTO(String image, String tel, String menu, String openingHours) {
		super();
		this.tel = tel;
		this.menu = menu;
		this.openingHours = openingHours;
		this.image = image;
	}
}
