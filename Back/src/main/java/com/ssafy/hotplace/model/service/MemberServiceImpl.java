package com.ssafy.hotplace.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.hotplace.model.MemberDTO;
import com.ssafy.hotplace.model.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	public MemberServiceImpl(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception {
		return memberRepository.login(memberDTO);
	}

	@Override
	public MemberDTO userInfo(String userId) throws Exception {
		return memberRepository.userInfo(userId);
	}

	@Override
	public MemberDTO register(MemberDTO memberDTO) throws Exception {
		return memberRepository.register(memberDTO);
	}
	
	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		memberRepository.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return memberRepository.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		memberRepository.deleteRefreshToken(map);
	}

	@Override
	public Optional<MemberDTO> loginByKakaoId(String id) throws Exception {
		return memberRepository.loginByKakaoId(id);
	}

}
