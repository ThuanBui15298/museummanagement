package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.ArchaeologicalExcavationsDTO;
import com.example.museummanagement.entity.ArchaeologicalExcavations;
import com.example.museummanagement.repository.ArchaeologicalExcavationsRepository;
import com.example.museummanagement.service.ArchaeologicalExcavationsService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArchaeologicalExcavationsServiceImpl implements ArchaeologicalExcavationsService {


    private final ArchaeologicalExcavationsRepository archaeologicalExcavationsRepository;

    @SneakyThrows
    @Override
    public ArchaeologicalExcavationsDTO createArchaeologicalExcavations(ArchaeologicalExcavationsDTO archaeologicalExcavationsDTO) {
        Optional<ArchaeologicalExcavations> optionalItinerantDisplay = archaeologicalExcavationsRepository.findByName(archaeologicalExcavationsDTO.getName());
        ArchaeologicalExcavations archaeologicalExcavations = new ArchaeologicalExcavations();
        if (optionalItinerantDisplay.isEmpty()){
            archaeologicalExcavations.setType(Constants.TYPE_ARCHAEOLOGICAL_EXCAVATIONS);
            archaeologicalExcavations.setName(archaeologicalExcavationsDTO.getName());
            archaeologicalExcavations.setTitle(archaeologicalExcavationsDTO.getTitle());
            archaeologicalExcavations.setSlug(archaeologicalExcavationsDTO.getSlug());
            archaeologicalExcavations.setContent(archaeologicalExcavationsDTO.getContent());
            archaeologicalExcavations.setAuthor(archaeologicalExcavationsDTO.getAuthor());
            archaeologicalExcavations.setStatus(Constants.STATUS_ACTIVE);
            archaeologicalExcavationsRepository.save(archaeologicalExcavations);
        } else {
            throw new Exception("Name existed!");
        }
        return archaeologicalExcavationsDTO;
    }

    @SneakyThrows
    @Override
    public ArchaeologicalExcavationsDTO updateArchaeologicalExcavations(ArchaeologicalExcavationsDTO archaeologicalExcavationsDTO, Long id) {
        Optional<ArchaeologicalExcavations> optionalArchaeologicalExcavations = archaeologicalExcavationsRepository.findById(id);
        ArchaeologicalExcavations archaeologicalExcavations = optionalArchaeologicalExcavations.get();
        if (optionalArchaeologicalExcavations.isPresent()) {
            Optional<ArchaeologicalExcavations> archaeologicalExcavation = archaeologicalExcavationsRepository.findByName(archaeologicalExcavationsDTO.getName());
            if (archaeologicalExcavation.isEmpty()){
                archaeologicalExcavations.setType(Constants.TYPE_ARCHAEOLOGICAL_EXCAVATIONS);
                archaeologicalExcavations.setName(archaeologicalExcavationsDTO.getName());
                archaeologicalExcavations.setTitle(archaeologicalExcavationsDTO.getTitle());
                archaeologicalExcavations.setSlug(archaeologicalExcavationsDTO.getSlug());
                archaeologicalExcavations.setContent(archaeologicalExcavationsDTO.getContent());
                archaeologicalExcavations.setAuthor(archaeologicalExcavationsDTO.getAuthor());
                archaeologicalExcavations.setStatus(Constants.STATUS_ACTIVE);
                archaeologicalExcavationsRepository.save(archaeologicalExcavations);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found id: " + id);
        }
        return archaeologicalExcavationsDTO;
    }

    @SneakyThrows
    @Override
    public void deleteArchaeologicalExcavations(Long id) {
        List<ArchaeologicalExcavations> archaeologicalExcavationsList = archaeologicalExcavationsRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(archaeologicalExcavationsList)) {
            throw new MessageDescriptorFormatException("No existed");
        }
        for (ArchaeologicalExcavations archaeologicalExcavations : archaeologicalExcavationsList) {
            archaeologicalExcavations.setStatus(Constants.STATUS_INACTIVE);
            archaeologicalExcavations.setModifiedDate(new Date());
            archaeologicalExcavationsRepository.save(archaeologicalExcavations);
        }
    }

    @Override
    public List<ArchaeologicalExcavations> findAll() {
        return archaeologicalExcavationsRepository.findAll();
    }
}
