package com.example.kal_l.serviceUser;

import com.example.kal_l.dtoUser.RegDto;
import com.example.kal_l.dtoUser.UserEmailDto;
import com.example.kal_l.repositoryUser.UserRepositoryImp;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
@Slf4j
public class UserServiceImp implements UserService {
    private UserRepositoryImp userRepImp;

    @Autowired
    public UserServiceImp(UserRepositoryImp userRepImp) {
        this.userRepImp = userRepImp;
    }
    public void createNewUser(RegDto regDto) {
        userRepImp.regNewUser(regDto);
    }
    public void findUser(String userName, String userPassword) {
        userRepImp.findUserByUserNameAndUserPassword(userName, userPassword);
    }
    public void deleteUser(String userName, String userPassword) {
        userRepImp.deleteUserByUserNameAndUserPassword(userName, userPassword);
    }
    public void updateUserPassword(String userPasswordNew, String userPassword) {
        userRepImp.queryUserByUserPassword(userPasswordNew, userPassword);
    }
    public void updateUserName(String userNameNew, String userName) {
        userRepImp.queryUserByUserName(userNameNew, userName);
    }
    public void sendEmail(UserEmailDto userEmailDto) {
        userRepImp.sendEmail(userEmailDto);
    }
    public void sendCode(String code) {
        userRepImp.sendCode(code);
    }

}
