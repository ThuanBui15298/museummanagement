package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.EducationalActivitiesDTO;
import com.example.museummanagement.entity.EducationalActivities;
import com.example.museummanagement.repository.EducationalActivitiesRepository;
import com.example.museummanagement.service.EducationalActivitiesService;
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
public class EducationalActivitiesServiceImpl implements EducationalActivitiesService {

    private final EducationalActivitiesRepository educationalActivitiesRepository;

    @SneakyThrows
    @Transactional
    @Override
    public EducationalActivitiesDTO createEducationalActivities(EducationalActivitiesDTO educationalActivitiesDTO) {
        Optional<EducationalActivities> optionalEducationalActivities = educationalActivitiesRepository.findByName(educationalActivitiesDTO.getName());
        EducationalActivities educationalActivities = new EducationalActivities();
        if (optionalEducationalActivities.isEmpty()){
            educationalActivities.setType(Constants.TYPE_EDUCATIONAL_ACTIVITIES);
            educationalActivities.setName(educationalActivitiesDTO.getName());
            educationalActivities.setTitle(educationalActivitiesDTO.getTitle());
            educationalActivities.setSlug(educationalActivitiesDTO.getSlug());
            educationalActivities.setContent(educationalActivitiesDTO.getContent());
            educationalActivities.setAuthor(educationalActivitiesDTO.getAuthor());
            educationalActivities.setStatus(Constants.STATUS_ACTIVE);
            educationalActivitiesRepository.save(educationalActivities);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return educationalActivitiesDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public EducationalActivitiesDTO updateEducationalActivities(EducationalActivitiesDTO educationalActivitiesDTO, Long id) {
        Optional<EducationalActivities> optionalEducationalActivities = educationalActivitiesRepository.findById(id);
        if (optionalEducationalActivities.isPresent()) {
            EducationalActivities educationalActivities = optionalEducationalActivities.get();
            Optional<EducationalActivities> educationalActivitiesOpt = educationalActivitiesRepository.findByName(educationalActivitiesDTO.getName());
            if (educationalActivitiesOpt.isEmpty() || educationalActivities.getId().equals(educationalActivitiesOpt.get().getId())){
                educationalActivities.setType(Constants.TYPE_EDUCATIONAL_ACTIVITIES);
                educationalActivities.setName(educationalActivitiesDTO.getName());
                educationalActivities.setTitle(educationalActivitiesDTO.getTitle());
                educationalActivities.setSlug(educationalActivitiesDTO.getSlug());
                educationalActivities.setContent(educationalActivitiesDTO.getContent());
                educationalActivities.setAuthor(educationalActivitiesDTO.getAuthor());
                educationalActivities.setStatus(Constants.STATUS_ACTIVE);
                educationalActivitiesRepository.save(educationalActivities);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found id: " + id);
        }
        return educationalActivitiesDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteEducationalActivities(Long id) {
        List<EducationalActivities> educationalActivitiesList = educationalActivitiesRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(educationalActivitiesList)) {
            throw new MessageDescriptorFormatException("No existed");
        }
        for (EducationalActivities educationalActivities : educationalActivitiesList) {
            educationalActivities.setStatus(Constants.STATUS_INACTIVE);
            educationalActivities.setModifiedDate(new Date());
            educationalActivitiesRepository.save(educationalActivities);
        }
    }

    @Override
    public List<EducationalActivities> findAllEducationalActivities() {
        return educationalActivitiesRepository.findAll();
    }
}
