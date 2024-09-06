package com.example.pastebin.servicePasta;

import com.example.pastebin.dtoPasta.PastaCreationDto;
import com.example.pastebin.dtoPasta.PastaFindDto;
import com.example.pastebin.entityPasta.Pasta;
import com.example.pastebin.exception.CustomNoSuchPasteException;
import com.example.pastebin.repositoryPasta.PastaRepositoryImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

import static com.example.pastebin.dtoPasta.PastaFindDto.toPasteFindDto;

@Service
@Builder
@Slf4j
public class PastaServiceImp implements PastaService{
    private PastaRepositoryImp pastaRepImp;

    private final ObjectMapper objectMapper;
    @Autowired
    public PastaServiceImp(PastaRepositoryImp pastaRepImp, ObjectMapper objectMapper) {
        this.pastaRepImp = pastaRepImp;
        this.objectMapper = objectMapper;
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
    public PastaFindDto findByUrl(String url) throws CustomNoSuchPasteException {
        try (var jedis = new Jedis("redis", 6379)){
            if (jedis.get(url) != null) {
                Pasta p = pastaRepImp.findPastaByUrl(url);
                pastaRepImp.queryPastaByViewsAndPastaName(p.getViews()+1L,p.getPastaName());
                Pasta pasta = objectMapper.readValue(jedis.get(url), Pasta.class);
                return toPasteFindDto(pasta);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Pasta p = pastaRepImp.findPastaByUrl(url);
        return toPasteFindDto(p);
    }
    public List<PastaFindDto> findTenPastas() {
        return pastaRepImp.findTenPastas().stream()
                .map(PastaFindDto::toPasteFindDto)
                .toList();
    }
}
