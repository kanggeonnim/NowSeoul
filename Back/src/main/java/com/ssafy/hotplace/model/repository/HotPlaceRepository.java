package com.ssafy.hotplace.model.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplace.model.HotplaceDTO;

@Mapper
public interface HotPlaceRepository {

	int writeHotPlace(List<HotplaceDTO> list) throws SQLException;
	int updateHotPlace(HotplaceDTO hotplace) throws SQLException;
	List<HotplaceDTO> listLiveInfo() throws SQLException;
}
