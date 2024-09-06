package com.example.kal_l.repositoryPasta;

import com.example.kal_l.configuration.CustomMail;
import com.example.kal_l.dtoPasta.PastaCreationDto;
import com.example.kal_l.dtoPasta.PastaLikeDto;
import com.example.kal_l.entityPasta.Lifetime;
import com.example.kal_l.entityPasta.Pasta;
import com.example.kal_l.entityPasta.PastaStatus;
import com.example.kal_l.exception.CustomNoSuchPasteException;
import com.example.kal_l.repositoryUser.UserRepositoryImp;
import com.example.kal_l.url.Status;
import com.example.kal_l.url.UrlRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class PastaRepositoryImp implements PastaRepository{
    private PastaRepository pastaRep;
    private UserRepositoryImp userRepImp;
    private JavaMailSender mailSender;
    private UrlRepository urlRepository;
    @Autowired
    public PastaRepositoryImp(@Qualifier("pastaRepository") PastaRepository pastaRep, UserRepositoryImp userRepImp,
                              JavaMailSender mailSender, UrlRepository urlRepository) {
        this.pastaRep = pastaRep;
        this.userRepImp = userRepImp;
        this.mailSender = mailSender;
        this.urlRepository = urlRepository;
    }
    public String savePasta(PastaCreationDto pastaCreationDto) {
        pastaCreationDto.setPastaStatus(PastaStatus.fromValue(pastaCreationDto.getStatus()));
        pastaCreationDto.setPastaLifetime(Lifetime.fromValue(pastaCreationDto.getLifetime()));
        String a = "http://localhost:8080/PasteKal/" +
                urlRepository.findFirstByStatus(Status.FREE).get().getBaseUrl();
        urlRepository.findFirstByStatus(Status.FREE).get().setStatus(Status.TAKEN);
        pastaRep.save(Pasta.builder()
                        .pastaName(pastaCreationDto.getPastaName())
                        .pastaText(pastaCreationDto.getPastaText())
                        .url(a)
                        .pastaStatus(pastaCreationDto.getPastaStatus())
                        .creationTime(Instant.now())
                        .lifetime(pastaCreationDto.getPastaLifetime())
                        .user(userRepImp.findUserByUserNameAndUserPassword(pastaCreationDto.getUserName(),
                                pastaCreationDto.getUserPassword()).get())
                        .likes(0L)
                        .views(0L)
                .build());
        return a;
    }
    public void queryPastaByViewsAndPastaName(Long viewsNew, String pastaName){
    }
    public void queryPastaByLikesAndPastaName(Long likesNew, String pastaName){
    }
    public Pasta findPastaByUrl(String url) throws CustomNoSuchPasteException {
        Pasta p = pastaRep.findPastaByUrl(url);
        if (p == null) throw new CustomNoSuchPasteException();
        pastaRep.queryPastaByViewsAndPastaName(p.getViews()+1L,p.getPastaName());
        return p;
    }
    public List<String> findTenPastas() {
        List<String> pastas = new ArrayList<>();
        for (long i = 1L; i < 11L && pastaRep.count() - i+1L != 0; i++) {
            Pasta p = pastaRep.findById(i).get();
            if (p.getPastaStatus() == PastaStatus.UNLISTED) {
                pastas.add("Pasta name: " + p.getPastaName() + "  *****  Pasta: " + p.getPastaText() +
                        "  *****  Status: " + p.getPastaStatus() + "  *****  Lifetime: " + p.getLifetime() +
                        "  *****  Views: " + p.getViews() + "  *****  Likes: " + p.getLikes());
            }
        }
        return pastas;
    }
    public Optional<Pasta> findPastaByPastaName(String pastaName) {
        return pastaRep.findPastaByPastaName(pastaName);
    }
    public void deletePastaByPastaName(String pastaName) {
        try (var jedis = new Jedis()) { jedis.del(pastaRep.findPastaByPastaName(pastaName).get().getUrl()); }
        pastaRep.deletePastaByPastaName(pastaName);
    }
    public void queryPastaByPastaName(String pastaNameNew, String pastaName){
        pastaRep.queryPastaByPastaName(pastaNameNew, pastaName);
    }
    public void addLike(PastaLikeDto pastaLikeDto) {
        Pasta p = pastaRep.findPastaByPastaName(pastaLikeDto.getPastaName()).get();
        pastaRep.queryPastaByLikesAndPastaName(p.getLikes()+1L, p.getPastaName());
        SimpleMailMessage mail = new CustomMail().sendLikeMail(p.getUser().getUserEmail(),
                pastaLikeDto.getUserName(), pastaLikeDto.getPastaName());
        mailSender.send(mail);
    }










    @Override
    public void flush() {

    }

    @Override
    public <S extends Pasta> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Pasta> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Pasta> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Pasta getOne(Long aLong) {
        return null;
    }

    @Override
    public Pasta getById(Long aLong) {
        return null;
    }

    @Override
    public Pasta getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Pasta> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Pasta> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Pasta> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Pasta> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Pasta> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Pasta> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Pasta, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Pasta> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Pasta> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Pasta> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Pasta> findAll() {
        return null;
    }

    @Override
    public List<Pasta> findAllById(Iterable<Long> longs) {
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
    public void delete(Pasta entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Pasta> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Pasta> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Pasta> findAll(Pageable pageable) {
        return null;
    }
}
