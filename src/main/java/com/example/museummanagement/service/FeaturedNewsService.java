package com.example.museummanagement.service;


import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.entity.FeaturedNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import javax.transaction.Transactional;

public interface FeaturedNewsService {

    @Transactional
    FeaturedNewsDTO createFeaturedNews(FeaturedNewsDTO featuredNewsDTO);

    @Transactional
    FeaturedNewsDTO updateFeaturedNews(FeaturedNewsDTO featuredNewsDTO, Long id)  ;

    void deleteFeaturedNews(Long id) ;

    Page<FeaturedNews> findAllFeaturedNews(Pageable pageable, FeaturedNewsDTO featuredNewsDTO);

}
