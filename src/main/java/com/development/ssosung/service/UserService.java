package com.development.ssosung.service;

import com.development.ssosung.domain.User;
import com.development.ssosung.dto.UserDto;
import com.development.ssosung.global.common.SsoSungApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    void createUser(UserDto.UserCreateRequest request);

    ResponseEntity<SsoSungApiResponse> readUser(UserDto.UserReadRequest request);

    void updateUser(UserDto.UserUpdateRequest request);

    void deleteUser(UserDto.UserDeleteRequest request);

    User getUser(String userName);

}
