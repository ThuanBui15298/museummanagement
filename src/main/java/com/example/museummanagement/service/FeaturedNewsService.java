package com.example.museummanagement.service;


import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.entity.FeaturedNews;


import javax.transaction.Transactional;

public interface FeaturedNewsService {

    @Transactional
    FeaturedNewsDTO createFeaturedNews(FeaturedNewsDTO featuredNewsDTO) throws Exception;

    @Transactional
    FeaturedNewsDTO updateFeaturedNews(FeaturedNewsDTO featuredNewsDTO, Long id)  ;

    void deleteFeaturedNews(Long id) ;

    Iterable<FeaturedNews> findAll();

}
