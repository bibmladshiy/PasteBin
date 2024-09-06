package com.example.pastebin.dtoPasta;

import com.example.pastebin.entityPasta.Lifetime;
import com.example.pastebin.entityPasta.Pasta;
import com.example.pastebin.entityPasta.PastaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PastaFindDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String pastaName;

    private String pastaText;

    private Instant creationTime;

    private Lifetime lifetime;

    private PastaStatus pastaStatus;

    private String userName;

    private Long views;
    public static PastaFindDto toPasteFindDto (Pasta pasta){
        return PastaFindDto.builder()
                .pastaName(pasta.getPastaName())
                .pastaText(pasta.getPastaText())
                .pastaStatus(pasta.getPastaStatus())
                .userName(pasta.getUser().getUserName())
                .creationTime(pasta.getCreationTime())
                .lifetime(pasta.getLifetime())
                .views(pasta.getViews())
                .build();
    }
}
