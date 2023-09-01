package com.development.ssosung.service;

import com.development.ssosung.domain.User;
import com.development.ssosung.dto.UserDto;

import java.util.List;

public interface UserService {

    void saveUser(UserDto.UserSaveRequest request);

    User getUser(String userName);

}
