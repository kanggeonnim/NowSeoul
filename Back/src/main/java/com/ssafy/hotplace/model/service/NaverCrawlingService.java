package com.ssafy.hotplace.model.service;

import java.io.IOException;

import com.ssafy.hotplace.model.NaverCrawlingDTO;

public interface NaverCrawlingService {
	public NaverCrawlingDTO getNaverData(String place) throws Exception;
}	
