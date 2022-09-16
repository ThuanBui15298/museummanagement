package com.example.museummanagement.service;


import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.NewsDTO;
import com.example.museummanagement.entity.FeaturedNews;


import javax.transaction.Transactional;

public interface FeaturedNewsService {

    @Transactional
    FeaturedNewsDTO createFeaturedNews(FeaturedNewsDTO FeaturedNewsDTO) throws Exception;

    @Transactional
    FeaturedNewsDTO updateFeaturedNews(FeaturedNewsDTO FeaturedNewsDTO, Long id)  ;

    void deleteFeaturedNews(Long id) ;

    Iterable<FeaturedNews> findAll();

}
