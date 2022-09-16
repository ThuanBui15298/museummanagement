package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.MediaDTO;
import com.example.museummanagement.entity.Media;
import com.example.museummanagement.repository.MediaRepository;
import com.example.museummanagement.service.MediaService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @SneakyThrows
    @Override
    @Transactional
    public MediaDTO createMedia(MediaDTO mediaDTO)  {
        Media media = new Media();
        media.setLink(mediaDTO.getLink());
        media.setObjectType(mediaDTO.getObjectType());
        media.setType(mediaDTO.getType());
        media.setStatus(Constants.STATUS_ACTIVE);
        mediaRepository.save(media);
        return mediaDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public MediaDTO updateMedia(MediaDTO mediaDTO, Long id) {
        Optional<Media> media = mediaRepository.findById(id);
        Media med = media.get();
        if (media.isPresent()) {
            med.setLink(mediaDTO.getLink());
            med.setObjectType(mediaDTO.getObjectType());
            med.setType(mediaDTO.getType());
            med.setStatus(Constants.STATUS_ACTIVE);
            mediaRepository.save(med);
        } else {
            throw new MessageDescriptorFormatException("Id khong ton tai");
        }
        return mediaDTO;
    }
    @SneakyThrows
    @Override
    public void deleteMedia(Long id) {
        List<Media> media = mediaRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(media)) {
            throw new MessageDescriptorFormatException("Khong ton tai");
        }
        for (Media medias : media) {
            medias.setStatus(Constants.STATUS_INACTIVE);
            medias.setModifiedDate(new Date());
            mediaRepository.save(medias);
        }

    }

    @Override
    public List<Media> findAll() {
        return mediaRepository.findAll();
    }
}
