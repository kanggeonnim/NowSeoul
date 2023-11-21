package com.ssafy.hotplace.model.repository;

import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplace.model.MemberDTO;

@Mapper
public interface MemberRepository {
	public Optional<MemberDTO> loginByKakaoId(String id);

	public MemberDTO login(MemberDTO memberDTO);

	public MemberDTO userInfo(String userId);

	public void saveRefreshToken(Map<String, String> map);

	public Object getRefreshToken(String userId);

	public void deleteRefreshToken(Map<String, String> map);

	public MemberDTO register(MemberDTO memberDTO);
}