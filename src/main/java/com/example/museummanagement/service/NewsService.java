package com.example.museummanagement.service;


import com.example.museummanagement.dto.NewsDTO;
import com.example.museummanagement.entity.News;

import javax.transaction.Transactional;

public interface NewsService {

    @Transactional
    NewsDTO createNews(NewsDTO newsDTO) throws Exception;

    @Transactional
    NewsDTO updateNews(NewsDTO newsDTO, Long id)  ;

    NewsDTO deleteNews( NewsDTO newsDTO, Long id) ;

    Iterable<News> findAll();

}
