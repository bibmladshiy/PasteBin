package com.example.pastebin.servicePasta;

import com.example.pastebin.dtoPasta.PastaCreationDto;
import com.example.pastebin.dtoPasta.PastaFindDto;
import com.example.pastebin.entityPasta.Pasta;
import com.example.pastebin.exception.CustomNoSuchPasteException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PastaService {
    String createPasta(PastaCreationDto pastaCreationDto) throws InterruptedException;
    void deletePasta(String pastaName);
    void updatePastaName(String pastaNameNew, String pastaName);
    PastaFindDto findByUrl(String url) throws CustomNoSuchPasteException;
    List<PastaFindDto> findTenPastas();
}
