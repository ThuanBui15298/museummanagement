package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "SELECT m.* FROM media m JOIN videos v WHERE v.id = m.video_id AND m.status = 1", nativeQuery = true)
    List<Media> findByVideoIdAndStatus();
}
