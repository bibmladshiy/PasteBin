package com.example.pastebin.servicePasta;

import com.example.pastebin.dtoPasta.PastaCreationDto;
import com.example.pastebin.exception.CustomNoSuchPasteException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PastaService {
    String createPasta(PastaCreationDto pastaCreationDto) throws InterruptedException;
    void deletePasta(String pastaName);
    void updatePastaName(String pastaNameNew, String pastaName);
    String findByUrl(String url) throws CustomNoSuchPasteException;
    List<String> findTenPastas();
}
