package com.ssafy.hotplace.controller;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.hotplace.model.service.social.KakaoService;

import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.ssafy.hotplace.model.KakaoDTO;
import com.ssafy.hotplace.model.Entity.MsgEntity;
@Slf4j
@RestController
//@RequestMapping("user")
public class AccountController {

    private final KakaoService kakaoService;
//    private final JwtUtil jwtUtil;
    
    @Autowired
    public AccountController(KakaoService kakaoService) {
		super();
		this.kakaoService = kakaoService;
	}

//    @GetMapping("/signin/kakao")
    @GetMapping("/kakao/callback")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
        
        return ResponseEntity.ok()
                .body(new MsgEntity("Success", kakaoInfo));
    }
//    //소셜 카카오 로그인
//    @ApiOperation(value = "kakao login api info", notes = "For login, using kakao open api. Need to get authorization code.")
//    @GetMapping("/user/signin/kakao")
//    public GlobalResponseDto<LoginResponseDto> kakaoLogin(@RequestParam String code,
//                                                          HttpServletResponse response) throws JsonProcessingException {
//
//        return kakaoAccountService.kakaoLogin(code, response);
//    }
//
//    //네이버 소셜 로그인
//    @ApiOperation(value = "naver login api info", notes = "For login using naver open api")
//    @GetMapping("/user/signin/naver")
//    public GlobalResponseDto<LoginResponseDto> naverLogin(@RequestParam String code,
//                                                          @RequestParam String state,
//                                                          HttpServletResponse response) throws IOException {
//        return naverAccountService.naverLogin(code, state, response);
//    }
//
//    //로그아웃
//    @ApiOperation(value = "logout api info", notes = "logout")
//    @DeleteMapping("api/logout")
//    public GlobalResponseDto<String> logout(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return kakaoAccountService.logout(userDetails.getAccount());
//    }
//
//
//    @ApiOperation(value = "reissue", notes = "토큰재발급")
//    @GetMapping("/user/reissue") // access token이 만료됐을 경우
//    public GlobalResponseDto issuedToken(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
//        response.addHeader(JwtUtil.ACCESS_TOKEN, JwtUtil.BEARER_PREFIX + " " + jwtUtil.createToken(userDetails.getAccount().getEmail(), "Access"));
//
//        return GlobalResponseDto.ok("재발급", null);
//    }
//
//    @ApiOperation(value = "회원탈퇴", notes = "회원탈퇴기능")
//    @GetMapping(value="/user/signout")
//    public GlobalResponseDto<?> signOut(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return accountService.signOut(userDetails.getAccount());
//    }


	

}