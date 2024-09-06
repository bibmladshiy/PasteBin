package com.example.kal_l.servicePasta;

import com.example.kal_l.dtoPasta.PastaCreationDto;
import com.example.kal_l.dtoPasta.PastaLikeDto;
import com.example.kal_l.exception.CustomNoSuchPasteException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PastaService {
    String createPasta(PastaCreationDto pastaCreationDto) throws InterruptedException;
    void deletePasta(String pastaName);
    void updatePastaName(String pastaNameNew, String pastaName);
    String findByUrl(String url) throws CustomNoSuchPasteException;
    List<String> findTenPastas();
    void addLike(PastaLikeDto pastaLikeDto);
}
