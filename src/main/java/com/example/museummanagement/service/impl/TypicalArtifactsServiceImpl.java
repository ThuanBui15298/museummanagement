package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.TypicalArtifactsDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.TypicalArtifacts;
import com.example.museummanagement.repository.TypicalArtifactsRepository;
import com.example.museummanagement.service.TypicalArtifactsService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypicalArtifactsServiceImpl implements TypicalArtifactsService {

    private final TypicalArtifactsRepository typicalArtifactsRepository;

    @SneakyThrows
    @Transactional
    @Override
    public TypicalArtifacts createTypicalArtifact(TypicalArtifacts typicalArtifacts) {
        Optional<TypicalArtifacts> typicalArtifactsOptional = typicalArtifactsRepository.findByName(typicalArtifacts.getName());
        TypicalArtifacts typicalArtifact = new TypicalArtifacts();
        if (typicalArtifactsOptional.isEmpty()) {
            typicalArtifact.setName(typicalArtifacts.getName());
            typicalArtifact.setTitle(typicalArtifacts.getTitle());
            typicalArtifact.setContent(typicalArtifacts.getContent());
            typicalArtifact.setSlug(typicalArtifacts.getSlug());
            typicalArtifact.setAuthor(typicalArtifacts.getAuthor());
            typicalArtifact.setStatus(Constants.STATUS_ACTIVE);
            typicalArtifact.setType(Constants.TYPE_TYPICAL_ARTIFACTS);
            typicalArtifactsRepository.save(typicalArtifact);
        } else {
            throw new Exception("Name existed!");
        }
        return typicalArtifacts;
    }

    @SneakyThrows
    @Transactional
    @Override
    public TypicalArtifacts updateTypicalArtifact(TypicalArtifacts typicalArtifacts, Long id) {
        Optional<TypicalArtifacts> optionalTypicalArtifacts = typicalArtifactsRepository.findById(id);
        if (optionalTypicalArtifacts.isPresent()) {
            TypicalArtifacts typicalArtifact = optionalTypicalArtifacts.get();
            Optional<TypicalArtifacts> optional = typicalArtifactsRepository.findByName(typicalArtifacts.getName());
            if (optional.isEmpty() || typicalArtifact.getId().equals(optional.get().getId())) {
                typicalArtifact.setName(typicalArtifacts.getName());
                typicalArtifact.setTitle(typicalArtifacts.getTitle());
                typicalArtifact.setContent(typicalArtifacts.getContent());
                typicalArtifact.setSlug(typicalArtifacts.getSlug());
                typicalArtifact.setAuthor(typicalArtifacts.getAuthor());
                typicalArtifact.setStatus(Constants.STATUS_ACTIVE);
                typicalArtifact.setType(Constants.TYPE_TYPICAL_ARTIFACTS);
                typicalArtifactsRepository.save(typicalArtifact);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found by this id!");
        }

        return typicalArtifacts;
    }

    @SneakyThrows
    @Override
    public void deleteTypicalArtifact(Long id) {
        List<TypicalArtifacts> typicalArtifactsList = typicalArtifactsRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(typicalArtifactsList)) {
            throw new Exception("Can not found!");
        }
        for (TypicalArtifacts typicalArtifacts: typicalArtifactsList) {
            typicalArtifacts.setStatus(Constants.STATUS_INACTIVE);
            typicalArtifactsRepository.save(typicalArtifacts);
        }
    }

    @Override
    public Page<TypicalArtifacts> findAllTypicalArtifacts(Pageable pageable, TypicalArtifactsDTO typicalArtifactsDTO) {
        String search;
        if (StringUtils.isEmpty(typicalArtifactsDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + typicalArtifactsDTO.getSearch().toLowerCase() + "%";
        }
        return typicalArtifactsRepository.findAllBySearch(pageable, search);
    }
}
