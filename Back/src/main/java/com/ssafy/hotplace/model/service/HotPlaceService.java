package com.ssafy.hotplace.model.service;

import java.util.List;

import com.ssafy.hotplace.model.LiveInfo;

public interface HotPlaceService {

	int insert(List<LiveInfo> list) throws Exception;
	
}
