package com.example.kal_l.repositoryPasta;

import com.example.kal_l.entityPasta.Pasta;
import com.example.kal_l.exception.CustomNoSuchPasteException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PastaRepository extends JpaRepository<Pasta, Long> {
    @Modifying
    @Transactional
    void deletePastaByPastaName(String pastaName);
    Optional<Pasta> findById(Long id);
    Pasta findPastaByUrl(String url) throws CustomNoSuchPasteException;
    Optional<Pasta> findPastaByPastaName(String pastaName);
    @Modifying
    @Transactional
    @Query("update Pasta p set p.views = ?1 where p.pastaName = ?2")
    void queryPastaByViewsAndPastaName(Long viewsNew, String pastaName);
    @Modifying
    @Transactional
    @Query("update Pasta p set p.pastaName = ?1 where p.pastaName = ?2")
    void queryPastaByPastaName(String pastaNameNew, String pastaName);
    @Modifying
    @Transactional
    @Query("update Pasta p set p.likes = ?1 where p.pastaName = ?2")
    void queryPastaByLikesAndPastaName(Long likesNew, String pastaName);
}
