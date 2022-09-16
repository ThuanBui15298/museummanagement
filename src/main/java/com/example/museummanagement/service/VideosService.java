package com.example.museummanagement.service;

import com.example.museummanagement.entity.Videos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideosService {

    Videos createVideo(Videos video);

    Videos upadateVideo(Videos video, Long id);

    void deleteVideo(Long id);

    List<Videos> getAllVideos();
}
