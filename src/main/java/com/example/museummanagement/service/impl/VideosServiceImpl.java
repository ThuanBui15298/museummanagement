package com.example.museummanagement.service.impl;

import com.example.museummanagement.entity.Videos;
import com.example.museummanagement.repository.VideosRepository;
import com.example.museummanagement.service.VideosService;
import com.example.museummanagement.ulti.Constants;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VideosServiceImpl implements VideosService {

    @Autowired
    private VideosRepository videosRepository;

    @SneakyThrows
    @Override
    public Videos createVideo(Videos video) {
        Optional<Videos> optionalVideos = videosRepository.findByName(video.getName());
        Videos videos = new Videos();
        if (optionalVideos.isEmpty()) {
            videos.setName(video.getName());
            videos.setSlug(video.getSlug());
            videos.setStatus(Constants.STATUS_ACTIVE);
            videosRepository.save(videos);
        } else {
            throw new Exception("Name existed");
        }
        return video;
    }

    @SneakyThrows
    @Override
    public Videos upadateVideo(Videos video, Long id) {
        Optional<Videos> optionalVideos = videosRepository.findById(id);
        Videos videos = optionalVideos.get();
        if (optionalVideos.isPresent()) {
            Optional<Videos> optional = videosRepository.findByName(video.getName());
            if (optional.isEmpty()) {
                videos.setName(video.getName());
                videos.setSlug(video.getSlug());
                videos.setStatus(Constants.STATUS_ACTIVE);
                videosRepository.save(videos);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new NoSuchElementException();
        }
        return video;
    }

    @SneakyThrows
    @Override
    public void deleteVideo(Long id) {
        List<Videos> videosList = videosRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(videosList)) {
            throw new Exception("Video not exsits");
        }
        for (Videos video : videosList) {
            video.setStatus(Constants.STATUS_INACTIVE);
            video.setCreatDate(new Date());
            videosRepository.save(video);
        }
    }

    @Override
    public List<Videos> getAllVideos() {
        return videosRepository.findAll();
    }
}
