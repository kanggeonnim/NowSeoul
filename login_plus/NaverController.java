package com.example.login.asd;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("naver")
public class NaverController {

    private final NaverService naverService;

    @GetMapping("/callback")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
        NaverDTO naverInfo = naverService.getNaverInfo(request.getParameter("code"));

        return ResponseEntity.ok()
                .body(new MsgEntity("Success", naverInfo));
    }

}