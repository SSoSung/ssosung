package com.development.ssosung.service;

import com.development.ssosung.domain.User;
import com.development.ssosung.dto.UserDto;
import com.development.ssosung.global.exception.BadRequestException;
import com.development.ssosung.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Service @RequiredArgsConstructor @Transactional @Slf4j
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
    public void saveUser(UserDto.UserSaveRequest request) {
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
    public User getUser(String userId) {
        log.info("Fetching user {}", userId);
        return userRepo.findByUserId(userId);
    }


}
