package com.ssafy.hotplace.repository;

import java.util.Optional;

import com.ssafy.hotplace.model.MemberDTO;

public interface MemberRepository {
    public Optional<MemberDTO> findByName(String name);
}