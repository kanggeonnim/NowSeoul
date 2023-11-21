package com.ssafy.hotplace.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberDTO {
    private String id; //기본키
    private String name; //유저 이름
    private String password; //유저 비밀번호
    private String email; //유저 구글 이메일
    private String role; //유저 권한 (일반 유저, 관리지ㅏ)
    private String provider; //공급자 (google, facebook ...)
    private String providerId; //공급 아이디
    private String joinDate;	//가입일
    private String refreshToken;	//토큰
    
    @Builder
    public MemberDTO(String id, String name, String password, String email, String role, String provider,
    		String providerId, String joinDate, String refreshToken) {
    	super();
    	this.id = id;
    	this.name = name;
    	this.password = password;
    	this.email = email;
    	this.role = role;
    	this.provider = provider;
    	this.providerId = providerId;
    	this.joinDate = joinDate;
    	this.refreshToken = refreshToken;
    }
    
	
}