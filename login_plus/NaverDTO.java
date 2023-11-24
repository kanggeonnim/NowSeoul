package com.example.login.asd;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NaverDTO {

    private String id;
    private String email;
    private String name;

}