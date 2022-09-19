package com.example.museummanagement.repository;

import com.example.museummanagement.entity.PublicationTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublicationTopicRepository extends JpaRepository<PublicationTopic, Long> {

    Optional<PublicationTopic> findByName(String name);

    List<PublicationTopic> findAllByIdAndStatus(Long id, Integer status);

}
