package com.ssafy.hotplace.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hotplace.model.LiveInfo;
import com.ssafy.hotplace.model.mapper.HotPlaceMapper;

@Service
public class HotPlaceServiceImpl implements HotPlaceService {

	private HotPlaceMapper hotplaceMapper;

	@Autowired
	public HotPlaceServiceImpl(HotPlaceMapper hotplaceMapper) {
		super();
		this.hotplaceMapper = hotplaceMapper;
	}


	@Override
	public int insert(List<LiveInfo> list) throws Exception {
		int result = hotplaceMapper.writeHotPlace(list);
		System.out.println(list);
		return result;
	}


}
