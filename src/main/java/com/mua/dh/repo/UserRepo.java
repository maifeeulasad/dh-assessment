package com.mua.dh.repo;

import com.mua.dh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select user from User user where user.username=?1")
    User findByUsername(String username);
}
