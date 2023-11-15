package com.ssafy.hotplace.model.service;

import java.util.List;

import com.ssafy.hotplace.model.PlaceDTO;

public interface PlaceService {

	List<PlaceDTO> listArticle() throws Exception;
	
}
