package com.ssafy.hotplace.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hotplace.model.PlaceInfo;
import com.ssafy.hotplace.model.mapper.PlaceMapper;

@Service
public class PlaceServiceImpl implements PlaceService {

	private PlaceMapper placeMapper;

	@Autowired
	public PlaceServiceImpl(PlaceMapper placeMapper) {
		super();
		this.placeMapper = placeMapper;
	}


	@Override
	public List<PlaceInfo> listArticle() throws Exception {
		List<PlaceInfo> list = placeMapper.listPlace();
		System.out.println(list);
		return list;
	}


}
