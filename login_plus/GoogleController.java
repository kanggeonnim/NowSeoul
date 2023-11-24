package com.example.login.asd;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("google")
public class GoogleController {

    private final GoogleService googleService;

//    @GetMapping("/callback")
    public String callbacks(HttpServletRequest request) throws Exception {
    	return googleService.requestAccessToken(request.getParameter("code"));
    }
    
    @GetMapping("/callback")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
        GoogleDTO googleInfo = googleService.getGoogleInfo(request.getParameter("code"));

        return ResponseEntity.ok()
                .body(new MsgEntity("Success", googleInfo));
    }

}