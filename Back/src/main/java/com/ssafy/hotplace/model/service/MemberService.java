package com.ssafy.hotplace.model.service;

import java.util.Optional;

import com.ssafy.hotplace.model.MemberDTO;

public interface MemberService {

	MemberDTO login(MemberDTO memberDTO) throws Exception;
	Optional<MemberDTO> loginByKakaoId(String id) throws Exception;
	MemberDTO userInfo(String userId) throws Exception;
	int register(MemberDTO memberDTO) throws Exception;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	
}
