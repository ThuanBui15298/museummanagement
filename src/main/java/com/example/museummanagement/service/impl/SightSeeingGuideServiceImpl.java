package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.SightSeeingGuideDTO;
import com.example.museummanagement.entity.SightSeeingGuide;
import com.example.museummanagement.repository.SightSeeingGuideRepository;
import com.example.museummanagement.service.SightSeeingGuideService;
import com.example.museummanagement.ulti.Constants;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SightSeeingGuideServiceImpl implements SightSeeingGuideService {

    @Autowired
    private SightSeeingGuideRepository sightSeeingGuideRepository;

    @SneakyThrows
    @Override
    public SightSeeingGuideDTO createSightSeeingGuide(SightSeeingGuideDTO sightSeeingGuideDTO) {
        Optional<SightSeeingGuide> optionalSightSeeingGuide = sightSeeingGuideRepository.findByName(sightSeeingGuideDTO.getName());
        SightSeeingGuide sightSeeingGuide = new SightSeeingGuide();
        if (optionalSightSeeingGuide.isEmpty()) {
            sightSeeingGuide.setName(sightSeeingGuideDTO.getName());
            sightSeeingGuide.setTitle(sightSeeingGuideDTO.getTitle());
            sightSeeingGuide.setContent(sightSeeingGuideDTO.getContent());
            sightSeeingGuide.setSlug(sightSeeingGuideDTO.getSlug());
            sightSeeingGuide.setAuthor(sightSeeingGuideDTO.getAuthor());
            sightSeeingGuide.setType(Constants.TYPE_SIGHTSEEING_GUIDE);
            sightSeeingGuide.setStatus(Constants.STATUS_ACTIVE);
            sightSeeingGuideRepository.save(sightSeeingGuide);
        } else {
          throw new Exception("Name existed!");
        }
        return sightSeeingGuideDTO;
    }

    @SneakyThrows
    @Override
    public SightSeeingGuideDTO updateSightSeeingGuide(SightSeeingGuideDTO sightSeeingGuideDTO, Long id) {
        Optional<SightSeeingGuide> optionalSightSeeingGuide = sightSeeingGuideRepository.findById(id);
        if (optionalSightSeeingGuide.isPresent()) {
            SightSeeingGuide sightSeeingGuide = optionalSightSeeingGuide.get();
            Optional<SightSeeingGuide> optional = sightSeeingGuideRepository.findByName(sightSeeingGuideDTO.getName());
            if (optional.isEmpty() || sightSeeingGuide.getId().equals(optional.get().getId())) {
                sightSeeingGuide.setName(sightSeeingGuideDTO.getName());
                sightSeeingGuide.setTitle(sightSeeingGuideDTO.getTitle());
                sightSeeingGuide.setContent(sightSeeingGuideDTO.getContent());
                sightSeeingGuide.setSlug(sightSeeingGuideDTO.getSlug());
                sightSeeingGuide.setAuthor(sightSeeingGuideDTO.getAuthor());
                sightSeeingGuide.setType(Constants.TYPE_SIGHTSEEING_GUIDE);
                sightSeeingGuide.setStatus(Constants.STATUS_ACTIVE);
                sightSeeingGuideRepository.save(sightSeeingGuide);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found by id: " + id);
        }
        return sightSeeingGuideDTO;
    }

    @SneakyThrows
    @Override
    public void deleteSightSeeingGuide(Long id) {
        List<SightSeeingGuide> sightSeeingGuideList = sightSeeingGuideRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(sightSeeingGuideList)) {
            throw new Exception("Can not found!");
        }

        for (SightSeeingGuide sightSeeingGuide : sightSeeingGuideList) {
            sightSeeingGuide.setStatus(Constants.STATUS_INACTIVE);
            sightSeeingGuideRepository.save(sightSeeingGuide);
        }
    }

    @Override
    public Page<SightSeeingGuide> findAllSightSeeingGuide(Pageable pageable, SightSeeingGuideDTO sightSeeingGuideDTO) {
        String search;
        if (StringUtils.isEmpty(sightSeeingGuideDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + sightSeeingGuideDTO.getSearch().toLowerCase() + "%";
        }
        return sightSeeingGuideRepository.findAllBySearch(pageable, search);
    }
}
