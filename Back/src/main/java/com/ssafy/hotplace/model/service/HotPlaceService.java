package com.ssafy.hotplace.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.hotplace.model.HotplaceDTO;

public interface HotPlaceService {

	int insert(List<HotplaceDTO> list) throws Exception;

	int update(List<HotplaceDTO> list) throws Exception;
	
	List<HotplaceDTO> listLiveInfo() throws Exception;
}
