package com.example.museummanagement.service;



import com.example.museummanagement.dto.PublicationTopicDTO;
import com.example.museummanagement.entity.PublicationTopic;

import javax.transaction.Transactional;
import java.util.List;

public interface PublicationTopicService {

    @Transactional
    PublicationTopicDTO createPublicationTopic(PublicationTopicDTO publicationTopicDTO);

    @Transactional
    PublicationTopicDTO updatePublicationTopic(PublicationTopicDTO publicationTopicDTO, Long id)  ;

    void deletePublicationTopic(Long id) ;

    List<PublicationTopic> findAllPublicationTopic();
}
