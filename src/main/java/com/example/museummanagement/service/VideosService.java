package com.example.museummanagement.service;

import com.example.museummanagement.entity.Videos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface VideosService {

    Videos createVideo(Videos video);

    Videos updateVideo(Videos video, Long id);

    Map<String, Object> deleteVideo(Long id);

    List<Videos> getAllVideos();
}
