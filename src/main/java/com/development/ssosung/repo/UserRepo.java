package com.development.ssosung.repo;


import com.development.ssosung.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    User findByUserId(String userId);

}
