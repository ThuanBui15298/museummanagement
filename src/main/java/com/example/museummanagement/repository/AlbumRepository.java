package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByName(String name);

    List<Album> findAllByIdAndStatus(Long id, Integer status);
}
