package com.example.museummanagement.service;



import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.PublicationTopicDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.PublicationTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface PublicationTopicService {

    @Transactional
    PublicationTopicDTO createPublicationTopic(PublicationTopicDTO publicationTopicDTO);

    @Transactional
    PublicationTopicDTO updatePublicationTopic(PublicationTopicDTO publicationTopicDTO, Long id)  ;

    void deletePublicationTopic(Long id) ;

    Page<PublicationTopic> findAllPublicationTopic(Pageable pageable, PublicationTopicDTO publicationTopicDTO);

}
