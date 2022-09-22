package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.OrganizationalStructureDTO;
import com.example.museummanagement.entity.OrganizationalStructure;
import com.example.museummanagement.repository.OrganizationalStructureRepository;
import com.example.museummanagement.service.OrganizationalStructureService;
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
public class OrganizationalStructureServiceImpl implements OrganizationalStructureService {

    private final OrganizationalStructureRepository organizationalStructureRepository;

    @SneakyThrows
    @Transactional
    @Override
    public OrganizationalStructureDTO createOrganizationalStructure(OrganizationalStructureDTO organizationalStructureDTO) {
        Optional<OrganizationalStructure> optionalOrganizationalStructure = organizationalStructureRepository.findByName(organizationalStructureDTO.getName());
        OrganizationalStructure organizationalStructure = new OrganizationalStructure();
        if (optionalOrganizationalStructure.isEmpty()) {
            organizationalStructure.setType(Constants.TYPE_ORGANIZATIONAL_STRUCTURE);
            organizationalStructure.setName(organizationalStructureDTO.getName());
            organizationalStructure.setTitle(organizationalStructureDTO.getTitle());
            organizationalStructure.setContent(organizationalStructureDTO.getContent());
            organizationalStructure.setStatus(Constants.STATUS_ACTIVE);
            organizationalStructureRepository.save(organizationalStructure);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return organizationalStructureDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public OrganizationalStructureDTO updateOrganizationalStructure(OrganizationalStructureDTO organizationalStructureDTO, Long id) {
        Optional<OrganizationalStructure> optionalOrganizationalStructure = organizationalStructureRepository.findById(id);
        if (optionalOrganizationalStructure.isPresent()) {
            OrganizationalStructure organizationalStructure = optionalOrganizationalStructure.get();
            Optional<OrganizationalStructure> organizationalStructureOpt = organizationalStructureRepository.findByName(organizationalStructureDTO.getName());
            if (organizationalStructureOpt.isEmpty() || organizationalStructure.getId().equals(organizationalStructureOpt.get().getId())) {
                organizationalStructure.setType(Constants.TYPE_ORGANIZATIONAL_STRUCTURE);
                organizationalStructure.setName(organizationalStructureDTO.getName());
                organizationalStructure.setTitle(organizationalStructureDTO.getTitle());
                organizationalStructure.setContent(organizationalStructureDTO.getContent());
                organizationalStructure.setStatus(Constants.STATUS_ACTIVE);
                organizationalStructureRepository.save(organizationalStructure);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found id: " + id);
        }
        return organizationalStructureDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteOrganizationalStructure(Long id) {
        List<OrganizationalStructure> organizationalStructureList = organizationalStructureRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(organizationalStructureList)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (OrganizationalStructure organizationalStructure : organizationalStructureList) {
            organizationalStructure.setStatus(Constants.STATUS_INACTIVE);
            organizationalStructure.setModifiedDate(new Date());
            organizationalStructureRepository.save(organizationalStructure);
        }
    }

    @Override
    public List<OrganizationalStructure> getAllOrganizationalStructure() {
        return organizationalStructureRepository.findAll();
    }


}
