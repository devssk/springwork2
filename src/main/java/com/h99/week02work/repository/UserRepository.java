package com.h99.week02work.repository;

import com.h99.week02work.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //닉네임 중복 확인용 username으로 찾기
    Optional<User> findByUsername(String username);
    //카카오 중복 확인용 kakaoid로 찾기
    Optional<User> findByKakaoId(Long kakaoId);
}
