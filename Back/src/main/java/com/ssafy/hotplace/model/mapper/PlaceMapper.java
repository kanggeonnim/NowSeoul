package com.ssafy.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplace.model.PlaceDTO;
@Mapper
public interface PlaceMapper {

	void writeLiveHotPlaceInfo(PlaceDTO placeInfo) throws SQLException;
//
//	void registerFile(BoardDto boardDto) throws Exception;

	List<PlaceDTO> listPlace() throws SQLException;

//	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
//
//	BoardDto getArticle(int articleNo) throws SQLException;
//
//	void updateHit(int articleNo) throws SQLException;
//
//	void modifyArticle(BoardDto boardDto) throws SQLException;
//
//	void deleteFile(int articleNo) throws Exception;
//
//	void deleteArticle(int articleNo) throws SQLException;
//
//	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
	
}
