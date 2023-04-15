package com.example.webnovel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel, Long> {

    @Query("select n from Novel n order by n.purchaseCount desc")
    public List<Novel> getBestSellers();
}
