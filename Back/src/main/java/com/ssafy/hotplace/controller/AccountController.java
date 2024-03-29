package com.ssafy.hotplace.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hotplace.model.KakaoDTO;
import com.ssafy.hotplace.model.MemberDTO;
import com.ssafy.hotplace.model.Entity.MsgEntity;
import com.ssafy.hotplace.model.service.MemberService;
import com.ssafy.hotplace.model.service.social.KakaoService;
import com.ssafy.hotplace.util.JWTUtil;

import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
@CrossOrigin(origins = { "*" })
public class AccountController {

	private final KakaoService kakaoService;
	private final MemberService memberService;
	private final JWTUtil jwtUtil;

	@Autowired
	public AccountController(KakaoService kakaoService, MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.kakaoService = kakaoService;
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

// https://kauth.kakao.com/oauth/authorize?client_id=97f3803597017d8afc5cd9b9a5696cfa&redirect_uri=http://localhost/kakao/callback&response_type=code

	@GetMapping("/kakao/login") String kang(){
		return "test";
	}
	@PostMapping("/kakao/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody String code) throws Exception {
//		code = code.substring(0,code.length()-1);
		System.out.println(code);
		KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(code);
//		System.out.println(kakaoInfo.getEmail());
		Optional<MemberDTO> userInfo = memberService.loginByKakaoId(String.valueOf(kakaoInfo.getId()));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;

		// 기존 회원인 경우
		if (userInfo.isPresent()) {
//			로그인 사용자 정보.
			MemberDTO member = memberService.userInfo("kakao" + String.valueOf(kakaoInfo.getId()));
			String accessToken = jwtUtil.createAccessToken(member.getId());
			String refreshToken = jwtUtil.createRefreshToken(member.getId());

			resultMap.put("userInfo", member);
//			발급받은 refresh token을 DB에 저장.
			memberService.saveRefreshToken(member.getId(), refreshToken);

//			JSON으로 token 전달.
			resultMap.put("access-token", accessToken);
			status = HttpStatus.ACCEPTED;
		}
		// 신규 회원인 경우(userInfo가 null인경우
		else {
			MemberDTO member = MemberDTO.builder().id("kakao" + String.valueOf(kakaoInfo.getId()))
					.name(String.valueOf(kakaoInfo.getNickname())).email(kakaoInfo.getEmail()).role("일반 회원")
					.provider("kakao").providerId(String.valueOf(kakaoInfo.getId())).build();

			String accessToken = jwtUtil.createAccessToken(member.getId());
			String refreshToken = jwtUtil.createRefreshToken(member.getId());
//			log.debug("access token : {}", accessToken);
//			log.debug("refresh token : {}", refreshToken);

//			발급받은 refresh token을 DB에 저장.
//			memberService.saveRefreshToken(member.getId(), refreshToken);
			member.setRefreshToken(refreshToken);

//			JSON으로 token 전달.
			resultMap.put("access-token", accessToken);
//			resultMap.put("refresh-token", refreshToken);

			status = HttpStatus.CREATED;

			memberService.register(member);
			
		}

//		System.out.println("userInfo is " + userInfo);
//		System.out.println(kakaoInfo.getId());
//		System.out.println(kakaoInfo.getEmail());
//		System.out.println(kakaoInfo.getNickname());
//		return ResponseEntity.ok().body(new MsgEntity("Success", kakaoInfo));
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDTO member) {
		log.debug("login user : {}", member);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDTO loginUser = memberService.login(member);
			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getId(), refreshToken);

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDTO member = memberService.userInfo(userId);
				resultMap.put("userInfo", member);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(
			@PathVariable("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDTO member, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, member);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(member.getId()))) {
				String accessToken = jwtUtil.createAccessToken(member.getId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("인증시간이 만료되었습니다. 로그인이 필요합니다.");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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