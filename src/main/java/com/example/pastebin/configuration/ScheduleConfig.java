package com.example.pastebin.configuration;

import com.example.pastebin.entityPasta.Pasta;
import com.example.pastebin.repositoryPasta.PastaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
@Slf4j
public class ScheduleConfig {
    private final PastaRepository pastaRep;

    @Autowired
    public ScheduleConfig(@Qualifier("pastaRepository") PastaRepository pastaRep) {
        this.pastaRep = pastaRep;
    }

    //Чистит бд с пастами, удаляя просроченные пасты
    @Async
    @Scheduled(fixedDelay = 5000, initialDelay = 900000000)
    @Transactional
    public void delayDeletePasta() {
        List<Pasta> listPasta1 = new ArrayList<>(pastaRep.findAll());
        for (long i = 1L; i <= pastaRep.count(); i++) {
            if (listPasta1.get((int) i) == null){
                continue;
            }
            if ((Instant.now().getEpochSecond() - listPasta1.get((int) i).getCreationTime().getEpochSecond())
                   > listPasta1.get((int) i).getLifetime().getDuration().toSeconds()) {
                try (var jedis = new Jedis("redis", 6379)) { jedis.del(listPasta1.get((int) i).getUrl()); }
                pastaRep.deleteById(i);

            }
        }
    }

    //Метод, закидывающий в кэш популярные пасты
    @Async
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void cachePopularPastas() {
        List<Pasta> listPasta2 = new ArrayList<>(pastaRep.findAll());
        for (long y = 0L; y < pastaRep.count(); y++) {
            if (listPasta2.get((int) y) == null){
                continue;
            }
            Pasta p = listPasta2.get((int) y);
            if (p.getViews() >= 30L) {
                try (var jedis = new Jedis("redis", 6379)) { jedis.set(p.getUrl(), "Pasta name: " + p.getPastaName() + "  *****  Pasta: " + p.getPastaText() +
                        "  *****  Status: " + p.getPastaStatus() + "  *****  Lifetime: " + p.getLifetime() +
                        "  *****  Views: " + p.getViews()); }
            }
        }
    }
}
