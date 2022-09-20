package com.example.museummanagement.service.impl;

import com.example.museummanagement.entity.TypicalArtifacts;
import com.example.museummanagement.repository.TypicalArtifactsRepository;
import com.example.museummanagement.service.TypicalArtifactsService;
import com.example.museummanagement.ulti.Constants;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TypicalArtifactsServiceImpl implements TypicalArtifactsService {

    @Autowired
    private TypicalArtifactsRepository typicalArtifactsRepository;

    @SneakyThrows
    @Override
    public TypicalArtifacts createTypicalArtifact(TypicalArtifacts typicalArtifacts) {
        Optional<TypicalArtifacts> typicalArtifactsOptional = typicalArtifactsRepository.findByName(typicalArtifacts.getName());
        TypicalArtifacts typicalArtifact = new TypicalArtifacts();
        if (typicalArtifactsOptional.isEmpty()) {
            typicalArtifact.setName(typicalArtifacts.getName());
            typicalArtifact.setTitle(typicalArtifacts.getTitle());
            typicalArtifact.setContent(typicalArtifacts.getContent());
            typicalArtifact.setStatus(Constants.STATUS_ACTIVE);
            typicalArtifact.setType(Constants.TYPE_TYPICAL_ARTIFACTS);
            typicalArtifactsRepository.save(typicalArtifact);
        } else {
            throw new Exception("Name existed!");
        }
        return typicalArtifacts;
    }

    @SneakyThrows
    @Override
    public TypicalArtifacts updateTypicalArtifact(TypicalArtifacts typicalArtifacts, Long id) {
        Optional<TypicalArtifacts> optionalTypicalArtifacts = typicalArtifactsRepository.findById(id);
        if (optionalTypicalArtifacts.isPresent()) {
            TypicalArtifacts typicalArtifact = optionalTypicalArtifacts.get();
            Optional<TypicalArtifacts> optional = typicalArtifactsRepository.findByName(typicalArtifacts.getName());
            if (optional.isEmpty()) {
                typicalArtifact.setName(typicalArtifacts.getName());
                typicalArtifact.setTitle(typicalArtifacts.getTitle());
                typicalArtifact.setContent(typicalArtifacts.getContent());
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
    public List<TypicalArtifacts> getAllTypicalArtifacts() {
        return typicalArtifactsRepository.findAll();
    }
}
