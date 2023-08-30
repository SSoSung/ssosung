package com.development.ssosung.domain.user.dao;

import com.development.ssosung.domain.user.dto.UserDto;
import com.development.ssosung.domain.user.entity.User;
import com.development.ssosung.domain.user.repository.UserRepo;
import com.development.ssosung.domain.user.service.UserServ;
import com.development.ssosung.domain.util.Role;
import com.development.ssosung.global.common.SsoSungApiResponse;
import com.development.ssosung.global.common.SsoSungStatus;
import com.development.ssosung.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserDao implements UserServ {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void signUp(UserDto.SignUpRequest request) {

        User user = userRepo.findById(request.getUserId()).orElse(null);

        if(user == null) {
            User newUser = User.builder()
                    .userId(request.getUserId())
                    .userPw(bCryptPasswordEncoder.encode(request.getUserPw()))
                    .userNm(request.getUserNm())
                    .email(request.getEmail())
                    .cellPhone(request.getCellPhone())
                    .userRole(Role.ROLE_USER.value())
                    .useYn("N")
                    .build();

            newUser.setCreUser("ssosung");

            userRepo.save(newUser);
        }else{
            throw new BadRequestException("이미 있는 아이디 입니다.");
        }

    }


    @Override
    @Transactional
    public ResponseEntity<SsoSungApiResponse> login(UserDto.LoginRequest request) {

        User user = userRepo.findById(request.getUserId()).orElseThrow(()-> new BadRequestException("없는 사용자 아이디 입니다."));

        if(!bCryptPasswordEncoder.matches(request.getUserPw(), user.getUserPw())){
            throw new BadRequestException("비밀번호가 틀렸습니다.");
        }

        // 엑세스토큰관 리프레쉬토큰을 발급

        SsoSungApiResponse apiResponse = new SsoSungApiResponse(SsoSungStatus.OK,"로그인을 완료하였습니다.");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
