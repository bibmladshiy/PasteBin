package com.example.pastebin.serviceUser;

import com.example.pastebin.dtoUser.RegDto;
import com.example.pastebin.dtoUser.UserEmailDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void createNewUser(RegDto regDto);
    void findUser(String userName, String userPassword);
    void deleteUser(String userName, String userPassword);
    void updateUserPassword(String userPasswordNew, String userPassword);
    void updateUserName(String userNameNew, String userName);
    void sendEmail(UserEmailDto userEmailDto);
    void sendCode(String code);
}
