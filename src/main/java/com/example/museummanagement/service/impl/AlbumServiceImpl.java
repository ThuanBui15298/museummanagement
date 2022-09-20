package com.example.museummanagement.service.impl;

import com.example.museummanagement.entity.Album;
import com.example.museummanagement.entity.Media;
import com.example.museummanagement.repository.AlbumRepository;
import com.example.museummanagement.repository.MediaRepository;
import com.example.museummanagement.service.AlbumService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private final MediaRepository mediaRepository;

    public static final String  ALBUM_MEDIA = "Album";

    @SneakyThrows
    @Override
    @Transactional
    public Album createAlbum(Album album) {
        Optional<Album> optional = albumRepository.findByName(album.getName());
        Album album1 = new Album();
        if (!optional.isPresent()) {
            album1.setName(album.getName());
            album1.setSlug(album.getSlug());
            album1.setStatus(Constants.STATUS_ACTIVE);
            albumRepository.save(album1);
        } else {
            throw new Exception("Name existed!");
        }
        return album;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Album updateAlbum(Album album, Long id) {
        Optional<Album> optional = albumRepository.findById(id);
        if (optional.isPresent()) {
            Album album1 = optional.get();

            Optional<Album> optional1 = albumRepository.findByName(album.getName());
            if (optional1.isEmpty()) {
                album1.setName(album.getName());
                album1.setSlug(album.getSlug());
                album1.setStatus(Constants.STATUS_ACTIVE);
                albumRepository.save(album1);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found album with id: " + id);
        }
        return album;
    }

    @SneakyThrows
    @Override
    public Map<String, Object> deleteAlbum(Long id) {
        List<Album> albumList = albumRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if(CollectionUtils.isEmpty(albumList)) {
            throw new Exception("Can not found!");
        }
        for (Album album : albumList) {
            album.setStatus(Constants.STATUS_INACTIVE);
            album.setCreatDate(new Date());
            albumRepository.save(album);
        }

        List<Media> mediaList = mediaRepository.findByAlbumIdAndStatus();
        if (CollectionUtils.isEmpty(mediaList)) {
            throw new Exception("Media not exists");
        }
        for (Media media: mediaList) {
            media.setStatus(Constants.STATUS_INACTIVE);
            media.setCreatDate(new Date());
            mediaRepository.save(media);
        }

        Map<String, Object> result = new HashMap<>();
        result.put(ALBUM_MEDIA, albumList);
        return result;
    }

    @Override
    public List<Album> getALlAlbum() {
        return albumRepository.findAll();
    }

}
