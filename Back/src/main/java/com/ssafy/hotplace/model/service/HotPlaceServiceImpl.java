package com.ssafy.hotplace.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hotplace.model.HotplaceDTO;
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
	public int insert(List<HotplaceDTO> list) throws Exception {
		int result = hotplaceMapper.writeHotPlace(list);
		return result;
	}

	@Override
	public int update(List<HotplaceDTO> list) {
		try {
			
			for(HotplaceDTO hotplace : list) {
					hotplaceMapper.updateHotPlace(hotplace);
			}
			
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public List<HotplaceDTO> listLiveInfo() throws Exception {
		return hotplaceMapper.listLiveInfo();
	}
}
