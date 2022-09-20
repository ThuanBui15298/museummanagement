package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CollectionsDTO;
import com.example.museummanagement.entity.Collections;
import com.example.museummanagement.repository.CollectionsRepository;
import com.example.museummanagement.service.CollectionsService;
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
public class CollectionsServiceImpl implements CollectionsService {
    private final CollectionsRepository collectionsRepository;

    @SneakyThrows
    @Transactional
    @Override
    public CollectionsDTO createCollections(CollectionsDTO collectionsDTO) {
        Optional<Collections> optionalCollections = collectionsRepository.findByName(collectionsDTO.getName());
        Collections collections = new Collections();
        if (optionalCollections.isEmpty()){
            collections.setType(Constants.TYPE_COLLECTIONS);
            collections.setName(collectionsDTO.getName());
            collections.setTitle(collectionsDTO.getTitle());
            collections.setSlug(collectionsDTO.getSlug());
            collections.setContent(collectionsDTO.getContent());
            collections.setAuthor(collectionsDTO.getAuthor());
            collections.setStatus(Constants.STATUS_ACTIVE);
            collectionsRepository.save(collections);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return collectionsDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public CollectionsDTO updateCollections(CollectionsDTO collectionsDTO, Long id) {
        Optional<Collections> optionalCollections = collectionsRepository.findById(id);
        Collections collections = optionalCollections.get();
        if (optionalCollections.isPresent()) {
            Optional<Collections> collectionsOpt = collectionsRepository.findByName(collectionsDTO.getName());
            if (collectionsOpt.isEmpty()){
                collections.setType(Constants.TYPE_COLLECTIONS);
                collections.setName(collectionsDTO.getName());
                collections.setTitle(collectionsDTO.getTitle());
                collections.setSlug(collectionsDTO.getSlug());
                collections.setContent(collectionsDTO.getContent());
                collections.setAuthor(collectionsDTO.getAuthor());
                collections.setStatus(Constants.STATUS_ACTIVE);
                collectionsRepository.save(collections);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found id: " + id);
        }
        return collectionsDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteCollections(Long id) {
        List<Collections> collections = collectionsRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(collections)) {
            throw new MessageDescriptorFormatException("No existed");
        }
        for (Collections collection : collections) {
            collection.setStatus(Constants.STATUS_INACTIVE);
            collection.setModifiedDate(new Date());
            collectionsRepository.save(collection);
        }
    }

    @Override
    public List<Collections> findAllCollections() {
        return collectionsRepository.findAll();
    }
}