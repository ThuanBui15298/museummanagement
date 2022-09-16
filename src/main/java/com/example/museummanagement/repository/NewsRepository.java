package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Category;
import com.example.museummanagement.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {


    Optional<News> findByName(String name);

    List<News> findAllByIdAndStatus(Long id, Integer status);

}
