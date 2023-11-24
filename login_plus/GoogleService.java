package com.example.login.asd;

import java.util.Base64;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleService {
	// https://antdev.tistory.com/72
	private final String GOOGLE_E_MAIL = "https://www.googleapis.com/auth/userinfo.profile";
	private final String GOOGLE_SNS_CLIENT_ID = "25526902806-89orako3kls91ov9dkair8iat52lq8pg.apps.googleusercontent.com";
	private final String GOOGLE_SNS_CALLBACK_URL = "http://localhost:1092/google/callback";
	private final String GOOGLE_SNS_CLIENT_SECRET = "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";
	private final String GOOGLE_SNS_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";

	public GoogleDTO getGoogleInfo(String code) throws Exception {
		String accessToken = "";
		String refreshToken = "";
		String idToken = "";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded");

			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("code", code);
			params.add("client_id", GOOGLE_SNS_CLIENT_ID);
			params.add("client_secret", GOOGLE_SNS_CLIENT_SECRET);
			params.add("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
			params.add("grant_type", "authorization_code");

//			String parameterString = params.entrySet().stream().map(x -> x.getKey() + "=" + x.getValue())
//					.collect(Collectors.joining("&"));

			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

			ResponseEntity<String> response = restTemplate.exchange(
					GOOGLE_SNS_TOKEN_BASE_URL,
					HttpMethod.POST,
					httpEntity,
					String.class);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

			accessToken = (String) jsonObj.get("access_token");
			refreshToken = (String) jsonObj.get("refresh_token");
			idToken = (String) jsonObj.get("id_token");

		} catch (Exception e) {
			throw new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다 :: " + GOOGLE_SNS_TOKEN_BASE_URL);
		}
		return getUserInfoWithToken(idToken);
	}
	
	private GoogleDTO getUserInfoWithToken(String idToken) throws Exception {
		
        //HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded");
        
        //HttpHeader 담기
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
        		"https://oauth2.googleapis.com/tokeninfo?id_token="+idToken,
        		HttpMethod.POST,
				httpEntity,
				String.class);

        //Response 데이터 파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj    = (JSONObject) jsonParser.parse(response.getBody());
        String email = (String) jsonObj.get("email");
        
        String name;
        String family_name;
        String given_name;
        
        name = (String) jsonObj.get("name");
        if (name == null) {
        	family_name = (String) jsonObj.get("family_name");
        	given_name = (String) jsonObj.get("given_name");
        	name = given_name+family_name;
        	if (family_name == null && given_name == null) {
        		name = email.substring(0, email.indexOf('@'));
        	}
        }
        
//        JSONObject account = (JSONObject) jsonObj.get("response");
//
//        String id = String.valueOf(account.get("id"));
//        String email = String.valueOf(account.get("email"));
//        String name = String.valueOf(account.get("name"));

//        return GoogleDTO.builder().email(email).build();
        return GoogleDTO.builder()
        		.email(email)
        		.name(name).build();
    }

//    public String getGoogleLogin() {
//        return google_AUTH_URI + "/o/oauth2/v2/auth"
//        		+ "?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly"
//        		+ "&access_type=offline"
//        		+ "&include_granted_scopes=true"
//        		+ "&response_type=code"
//                + "&redirect_uri=" + google_REDIRECT_URL
//                + "&client_id=" + google_CLIENT_ID;
//    }

	public String requestAccessToken(String code) {
		try {
			URL url = new URL(GOOGLE_SNS_TOKEN_BASE_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);

			Map<String, Object> params = new HashMap<>();
			params.put("code", code);
			params.put("client_id", GOOGLE_SNS_CLIENT_ID);
			params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
			params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
			params.put("grant_type", "authorization_code");

			String parameterString = params.entrySet().stream().map(x -> x.getKey() + "=" + x.getValue())
					.collect(Collectors.joining("&"));

			BufferedOutputStream bous = new BufferedOutputStream(conn.getOutputStream());
			bous.write(parameterString.getBytes());
			bous.flush();
			bous.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			if (conn.getResponseCode() == 200) {
				return sb.toString();
			}
			return "구글 로그인 요청 처리 실패";
		} catch (IOException e) {
			throw new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다 :: " + GOOGLE_SNS_TOKEN_BASE_URL);
		}
	}
}