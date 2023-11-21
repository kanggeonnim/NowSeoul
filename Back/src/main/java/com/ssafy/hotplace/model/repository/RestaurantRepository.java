package com.ssafy.hotplace.model.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplace.model.HotplaceDTO;
import com.ssafy.hotplace.model.RestaurantDTO;

@Mapper
public interface RestaurantRepository {

	int writeRestaurants(List<RestaurantDTO> list) throws SQLException;
	int updateRestaurants(RestaurantDTO restaurant) throws SQLException;
}
