package com.ssafy.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplace.model.LiveInfo;

@Mapper
public interface HotPlaceMapper {

	int writeHotPlace(List<LiveInfo> list) throws SQLException;
}