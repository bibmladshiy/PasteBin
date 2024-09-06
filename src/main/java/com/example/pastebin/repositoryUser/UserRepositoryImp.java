package com.example.kal_l.repositoryUser;

import com.example.kal_l.configuration.CustomMail;
import com.example.kal_l.dtoUser.RegDto;
import com.example.kal_l.dtoUser.UserEmailDto;
import com.example.kal_l.entityUser.User;

import com.example.kal_l.exception.CustomDeletingException;
import com.example.kal_l.exception.CustomLoggingInException;
import com.example.kal_l.exception.CustomWrongCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImp implements UserRepository {
    private UserRepository userRep;
    private JavaMailSender mailSender;
    private SimpleMailMessage mail = null;
    @Autowired
    public UserRepositoryImp(@Qualifier("userRepository") UserRepository userRep, JavaMailSender mailSender) {
        this.userRep = userRep;
        this.mailSender = mailSender;
    }
    public void sendEmail(UserEmailDto userEmailDto) {
        mail = new CustomMail().sendRegMail(userEmailDto.getUserEmail());
        mailSender.send(mail);
    }

    public void sendCode(String code) {
        if (code.equalsIgnoreCase(mail.getText())){
            log.info("ok");
        }
        else throw new CustomWrongCodeException();
    }
    public void regNewUser(RegDto regDto){
        userRep.save(User.builder()
                .userName(regDto.getUserName())
                .userEmail(regDto.getUserEmail())
                .userPassword(regDto.getUserPassword())
                .build());
    }
    public Optional<User> findUserByUserNameAndUserPassword(String userName, String userPassword) {
        userRep.findUserByUserNameAndUserPassword(userName, userPassword).orElseThrow(
                () -> new CustomLoggingInException()
        );
        return userRep.findUserByUserNameAndUserPassword(userName, userPassword);
    }
    public Optional<User> deleteUserByUserNameAndUserPassword(String userName, String userPassword) {
        if(userRep.findUserByUserNameAndUserPassword(userName, userPassword).orElse(null) != null) {
            userRep.deleteUserByUserNameAndUserPassword(userName, userPassword);
        }
        else throw new CustomDeletingException();
        return null;
    }
    public void queryUserByUserPassword(String userPasswordNew, String userPassword) {
        userRep.queryUserByUserPassword(userPasswordNew, userPassword);
    }
    public void queryUserByUserName(String userNameNew, String userName) {
        userRep.queryUserByUserName(userNameNew, userName);
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Long aLong) {
        return null;
    }

    @Override
    public User getById(Long aLong) {
        return null;
    }

    @Override
    public User getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }


    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }
}
