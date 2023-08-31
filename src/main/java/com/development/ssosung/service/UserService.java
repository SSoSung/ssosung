package com.development.ssosung.service;

import com.development.ssosung.domain.Role;
import com.development.ssosung.domain.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    User getUser(String userName);

    List<User> getUsers();
}
