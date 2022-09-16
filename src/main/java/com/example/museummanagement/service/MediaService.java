package com.example.museummanagement.service;

import com.example.museummanagement.dto.MediaDTO;
import com.example.museummanagement.entity.Media;

import javax.transaction.Transactional;
import java.util.List;

public interface MediaService {

    @Transactional
    MediaDTO createMedia(MediaDTO mediaDTO);

    @Transactional
    MediaDTO updateMedia(MediaDTO mediaDTO, Long id)  ;

    void deleteMedia( Long id) ;

    List<Media> getAllMedia();
}
