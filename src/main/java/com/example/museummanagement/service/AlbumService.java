package com.example.museummanagement.service;

import com.example.museummanagement.dto.AlbumDTO;
import com.example.museummanagement.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public interface AlbumService {
    @Transactional
    Album createAlbum(Album album);

    @Transactional
    Album updateAlbum(Album album, Long id);

    Map<String, Object> deleteAlbum(Long id);

    Page<Album> getAllAlbum(Pageable pageable, AlbumDTO albumDTO);
}
