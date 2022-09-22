package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByName(String name);

    List<Album> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from album a where a.status = 1 and lower(concat(coalesce(a.name,''), coalesce(a.slug ,''))) like lower(:search)", nativeQuery = true)
    Page<Album> findBySearch(Pageable pageable, @Param("search") String search);
}
