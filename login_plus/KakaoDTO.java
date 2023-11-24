package com.example.login.asd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class KakaoDTO {

    private long id;
    private String email;
    private String nickname;
    
}