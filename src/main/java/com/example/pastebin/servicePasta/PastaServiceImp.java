package com.example.kal_l.servicePasta;

import com.example.kal_l.dtoPasta.PastaCreationDto;
import com.example.kal_l.dtoPasta.PastaLikeDto;
import com.example.kal_l.entityPasta.Pasta;
import com.example.kal_l.exception.CustomNoSuchPasteException;
import com.example.kal_l.repositoryPasta.PastaRepositoryImp;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
@Builder
@Slf4j
public class PastaServiceImp implements PastaService{
    private PastaRepositoryImp pastaRepImp;

    @Autowired
    public PastaServiceImp(PastaRepositoryImp pastaRepImp) {
        this.pastaRepImp = pastaRepImp;
    }
    public String createPasta(PastaCreationDto pastaCreationDto) {
        return pastaRepImp.savePasta(pastaCreationDto);
    }
    public void deletePasta(String pastaName) {
        pastaRepImp.deletePastaByPastaName(pastaName);
    }
    public void updatePastaName(String pastaNameNew, String pastaName){
        pastaRepImp.queryPastaByPastaName(pastaNameNew, pastaName);
    }
    public String findByUrl(String url) throws CustomNoSuchPasteException {
        try (var jedis = new Jedis()){
            if (jedis.get(url) != null) {
                Pasta p = pastaRepImp.findPastaByUrl(url);
                pastaRepImp.queryPastaByViewsAndPastaName(p.getViews()+1L,p.getPastaName());
                return jedis.get(url);
            }
        }
        Pasta p = pastaRepImp.findPastaByUrl(url);
        return "Pasta name: " + p.getPastaName() + "  *****  Pasta: " + p.getPastaText() +
                "  *****  Status: " + p.getPastaStatus() + "  *****  Lifetime: " + p.getLifetime() +
                "  *****  Views: " + p.getViews() + "  *****  Likes: " + p.getLikes();
    }
    public List<String> findTenPastas() {
        return pastaRepImp.findTenPastas();
    }
    public void addLike(PastaLikeDto pastaLikeDto){
        pastaRepImp.addLike(pastaLikeDto);
    }
}
