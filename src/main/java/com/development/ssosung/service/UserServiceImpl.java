package com.development.ssosung.service;

import com.development.ssosung.domain.User;
import com.development.ssosung.dto.UserDto;
import com.development.ssosung.global.common.SsoSungApiResponse;
import com.development.ssosung.global.common.SsoSungStatus;
import com.development.ssosung.global.exception.BadRequestException;
import com.development.ssosung.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepo.findByUserId(userId);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database : {}", userId);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getUserPw(), authorities);
    }


    @Override
    @Transactional
    public void createUser(UserDto.UserCreateRequest request) {
        log.info("Saving new user {} to the database", request.getUserNm());
        request.setUserPw(passwordEncoder.encode(request.getUserPw()));

        Optional<User> user = userRepo.findById(request.getUserId());

        if(user.isPresent()) {
            throw new BadRequestException("이미 있는 유저아이디 입니다.");
        }else{
            User newUser = User.builder()
                    .userId(request.getUserId())
                    .userPw(request.getUserPw())
                    .userNm(request.getUserNm())
                    .userTel(request.getUserTel())
                    .userRole("ROLE_USER")
                    .build();

            userRepo.save(newUser);
        }

    }


    @Override
    public ResponseEntity<SsoSungApiResponse> readUser(UserDto.UserReadRequest request) {

        User user = userRepo.findById(request.getUserId()).orElseThrow(()-> new BadRequestException("없는 사용자 입니다."));

        UserDto.UserReadResponse response = UserDto.UserReadResponse.builder()
                .userId(user.getUserId())
                .userNm(user.getUserNm())
                .userTel(user.getUserTel())
                .build();

        SsoSungApiResponse apiResponse = new SsoSungApiResponse(SsoSungStatus.OK, "사용자조회성공", response);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @Override
    @Transactional
    public void updateUser(UserDto.UserUpdateRequest request) {

        User user = userRepo.findById(request.getUserId()).orElseThrow(()-> new BadRequestException("없는 사용자 입니다."));

        user.update(request.getUserNm(), request.getUserTel());
    }

    @Override
    @Transactional
    public void deleteUser(UserDto.UserDeleteRequest request) {

        User user = userRepo.findById(request.getUserId()).orElseThrow(()-> new BadRequestException("없는 사용자 입니다."));
        // 일단 사용자를 DB에서 진짜로 삭제시킴. 나중에 상태값으로 변경 예정
        userRepo.delete(user);
    }

    @Override
    public User getUser(String userId) {
        log.info("Fetching user {}", userId);
        return userRepo.findByUserId(userId);
    }


}
