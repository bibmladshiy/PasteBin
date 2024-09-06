package com.example.kal_l.repositoryUser;

import com.example.kal_l.entityUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserNameAndUserPassword(String userName, String userPassword);
    @Modifying
    @Transactional
    Optional<User> deleteUserByUserNameAndUserPassword(String userName, String userPassword);
    @Modifying
    @Transactional
    @Query("update User u set u.userPassword = ?1 where u.userPassword = ?2")
    void queryUserByUserPassword(String userPasswordNew, String userPassword);
    @Modifying
    @Transactional
    @Query("update User u set u.userName = ?1 where u.userName = ?2")
    void queryUserByUserName(String userNameNew, String userName);
}

