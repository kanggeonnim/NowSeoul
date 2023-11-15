package com.ssafy.hotplace.model.service;

import java.util.List;

import com.ssafy.hotplace.model.HotplaceDTO;

public interface HotPlaceService {

	int insert(List<HotplaceDTO> list) throws Exception;

	int update(List<HotplaceDTO> list) throws Exception;
	
}
