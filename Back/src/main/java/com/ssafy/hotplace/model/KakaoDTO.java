package com.ssafy.hotplace.model;

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