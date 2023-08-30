package com.development.ssosung.domain.user.repository;

import com.development.ssosung.domain.user.entity.User;
import com.development.ssosung.domain.user.entity.id.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

}
