package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Category;
import com.example.museummanagement.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllByIdAndStatus(Long id, Integer status);
}
