package com.example.kal_l.configuration;

import com.example.kal_l.entityPasta.Pasta;
import com.example.kal_l.repositoryPasta.PastaRepository;
import com.example.kal_l.url.Status;
import com.example.kal_l.url.UrlEntity;
import com.example.kal_l.url.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.util.Base64;

@Configuration
@EnableScheduling
@Slf4j
public class ScheduleConfig {
    long i=1L;
    private final PastaRepository pastaRep;
    private final UrlRepository urlRepository;
    @Autowired
    public ScheduleConfig(@Qualifier("pastaRepository") PastaRepository pastaRep, UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
        this.pastaRep = pastaRep;
    }
    //Чистит бд с пастами, удаляя просроченные пасты
    @Async
    @Scheduled(fixedDelay = 5000, initialDelay = 900000000)
    public void delayDeletePasta() {
        for (Long i = 1L; i <= pastaRep.count(); i++) {
            pastaRep.findById(i);
            if ((Instant.now().getEpochSecond() - pastaRep.findById(i).get().getCreationTime().getEpochSecond())
                   > pastaRep.findById(i).get().getLifetime().getDuration().toSeconds()) {
                try (var jedis = new Jedis()) { jedis.del(pastaRep.findById(i).get().getUrl()); }
                pastaRep.deleteById(i);

            }
        }
    }
    //Создает какое-то конечное кол-во уникальных ссылок для паст
    @Async
    @Scheduled(fixedDelay = 1800000)
    public void createUrl() {
        while (i<100L){
            urlRepository.save(UrlEntity.builder()
                    .status(Status.FREE)
                    .build());
            urlRepository.queryUrlEntityByBaseUrl(Base64.getUrlEncoder().encodeToString(
                    urlRepository.findById(i).get().getId().toString().getBytes()), i);
            i++;
        }
    }
    //Метод, закидывающий в кэш популярные пасты
    @Async
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void cachePopularPastas() {
        for (long y = 1L; y <= pastaRep.count(); y++) {
            Pasta p = pastaRep.findById(y).get();
            if (p.getViews() >= 30L) {
                try (var jedis = new Jedis()) { jedis.set(p.getUrl(), "Pasta name: " + p.getPastaName() + "  *****  Pasta: " + p.getPastaText() +
                        "  *****  Status: " + p.getPastaStatus() + "  *****  Lifetime: " + p.getLifetime() +
                        "  *****  Views: " + p.getViews() + "  *****  Likes: " + p.getLikes()); }
            }
        }
    }
}
