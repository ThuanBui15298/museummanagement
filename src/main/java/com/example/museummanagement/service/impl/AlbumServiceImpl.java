package com.example.museummanagement.service.impl;

import com.example.museummanagement.entity.Album;
import com.example.museummanagement.exception.ExistedNameException;
import com.example.museummanagement.repository.AlbumRepository;
import com.example.museummanagement.service.AlbumService;
import com.example.museummanagement.ulti.Constants;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

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
            throw new ExistedNameException();
        }
        return album;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Album updateAlbum(Album album, Long id) {
        Optional<Album> optional = albumRepository.findById(id);
        Album album1 = new Album();
        if (optional.isPresent()) {
            Optional<Album> optional1 = albumRepository.findByName(album.getName());
            if (optional1.isEmpty()) {
                album1.setName(album.getName());
                album1.setSlug(album.getSlug());
                album1.setStatus(Constants.STATUS_ACTIVE);
                albumRepository.save(album1);
            } else {
                throw new ExistedNameException();
            }
        } else {
            throw new Exception("Can not found album with id: " + id);
        }
        return album;
    }

    @SneakyThrows
    @Override
    public Album deleteAlbum(Album album, Long id) {
        List<Album> albumList = albumRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if(CollectionUtils.isEmpty(albumList)) {
            throw new Exception("Can not found!");
        }
        for (Album album1 : albumList) {
            album1.setStatus(Constants.STATUS_INACTIVE);
            album1.setCreatDate(new Date());
            albumRepository.save(album1);
        }
        return album;
    }

    @Override
    public List<Album> getALlAlbum() {
        return albumRepository.findAll();
    }

}
