package com.ssafy.hotplace.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hotplace.model.PlaceDTO;
import com.ssafy.hotplace.model.repository.PlaceRepository;

@Service
public class PlaceServiceImpl implements PlaceService {

	private PlaceRepository placeMapper;

	@Autowired
	public PlaceServiceImpl(PlaceRepository placeMapper) {
		super();
		this.placeMapper = placeMapper;
	}


	@Override
	public List<PlaceDTO> listArticle() throws Exception {
		List<PlaceDTO> list = placeMapper.listPlace();
		return list;
	}


}
