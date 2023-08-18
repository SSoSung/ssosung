package com.development.ssosung.domain.user.dao;

import com.development.ssosung.domain.user.dto.UserDto;
import com.development.ssosung.domain.user.entity.User;
import com.development.ssosung.domain.user.entity.id.UserId;
import com.development.ssosung.domain.user.repository.UserRepo;
import com.development.ssosung.domain.user.service.UserServ;
import com.development.ssosung.domain.util.Role;
import com.development.ssosung.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDao implements UserServ {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void signUp(UserDto.SignUpRequest request) {

        userRepo.findById(new UserId(request.getUserId())).orElseThrow(()-> new BadRequestException("사용중인 ID 입니다."));

        User newUser = User.builder()
                .userId(request.getUserId())
                .userPw(bCryptPasswordEncoder.encode(request.getUserPw()))
                .userNm(request.getUserNm())
                .email(request.getEmail())
                .cellPhone(request.getCellPhone())
                .userRole(Role.ROLE_USER.value())
                .useYn("N")
                .build();

        userRepo.save(newUser);
    }
}
