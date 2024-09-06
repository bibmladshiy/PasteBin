package com.example.pastebin.entityPasta;

import com.example.pastebin.entityUser.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "pasta")
@Entity
public class Pasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "pasta_name")
    private String pastaName;

    @Column(name = "pasta_text")
    private String pastaText;

    @Column(name = "url")
    private String url;

    @Column(name = "creation_time")
    private Instant creationTime;

    @Column(name = "lifetime")
    @Enumerated(EnumType.STRING)
    private Lifetime lifetime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PastaStatus pastaStatus;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long views;

}
