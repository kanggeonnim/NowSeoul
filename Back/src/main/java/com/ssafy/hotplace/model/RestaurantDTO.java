package com.ssafy.hotplace.model;

import lombok.Builder;
import lombok.Data;

@Data
public class RestaurantDTO {
	private String manageNo;
	private String detaileStateName;
	private String tel;
	private String postNo;
	private String oldAddr;
	private String streetAddr;
	private String businessplaceName;
	private String uptaeName;
	private String x;
	private String y;
	private String menu;
	private String openingHours;
	
	@Builder
	public RestaurantDTO(String manageNo, String detaileStateName, String tel, String postNo, String oldAddr,
			String streetAddr, String businessplaceName, String uptaeName, String x, String y, String menu,
			String openingHours) {
		super();
		this.manageNo = manageNo;
		this.detaileStateName = detaileStateName;
		this.tel = tel;
		this.postNo = postNo;
		this.oldAddr = oldAddr;
		this.streetAddr = streetAddr;
		this.businessplaceName = businessplaceName;
		this.uptaeName = uptaeName;
		this.x = x;
		this.y = y;
		this.menu = menu;
		this.openingHours = openingHours;
	}
	
	
}
