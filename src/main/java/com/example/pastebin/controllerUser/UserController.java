package com.example.pastebin.controllerUser;

import com.example.pastebin.dtoUser.*;
import com.example.pastebin.serviceUser.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/PasteBin")
public class UserController {
    private UserServiceImp userServImp;
    @Autowired
    public UserController(UserServiceImp userServImp) {
        this.userServImp = userServImp;
    }

    @GetMapping("/send-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendEmail(@RequestBody UserEmailDto userEmailDto){
        userServImp.sendEmail(userEmailDto);
    }

    @GetMapping("/send-code")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendCode(@RequestBody String code){
        userServImp.sendCode(code);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfile(@Validated @RequestBody RegDto regDto) {
        userServImp.createNewUser(regDto);
        log.info(String.valueOf(Thread.currentThread()));
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void findProfile(@RequestBody LogInDto logInDto){
        userServImp.findUser(logInDto.getUserName(), logInDto.getUserPassword());
    }

    @DeleteMapping("/delete-user")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteProfile(@RequestBody LogInDto logInDto) {
        userServImp.deleteUser(logInDto.getUserName(), logInDto.getUserPassword());
    }

    @PutMapping("/update-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProfilePassword(@Validated @RequestBody UpdatePasswordDto updatePasswordDto) {
        userServImp.updateUserPassword(updatePasswordDto.getUserPasswordNew(), updatePasswordDto.getUserPassword());
    }

    @PutMapping("/update-name")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProfileName(@Validated @RequestBody UpdateNameDto updateNameDto) {
        userServImp.updateUserName(updateNameDto.getUserNameNew(), updateNameDto.getUserName());
    }


}
