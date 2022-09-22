package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.repository.FeaturedNewsRepository;
import com.example.museummanagement.service.FeaturedNewsService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeaturedNewsServiceImpl implements FeaturedNewsService {

    private final FeaturedNewsRepository featuredNewsRepository;

    @SneakyThrows
    @Transactional
    @Override
    public FeaturedNewsDTO createFeaturedNews(FeaturedNewsDTO featuredNewsDTO) {
        validRequest(featuredNewsDTO);
        Optional<FeaturedNews> featuredNewsName = featuredNewsRepository.findByName(featuredNewsDTO.getName());
        FeaturedNews featuredNews = new FeaturedNews();
        if (featuredNewsName.isEmpty()) {
            featuredNews.setName(featuredNewsDTO.getName());
            featuredNews.setContent(featuredNewsDTO.getContent());
            featuredNews.setType(Constants.TYPE_FEATURED_NEWS);
            featuredNews.setTitle(featuredNewsDTO.getTitle());
            featuredNews.setSlug(featuredNewsDTO.getSlug());
            featuredNews.setAuthor(featuredNewsDTO.getAuthor());
            featuredNews.setStatus(Constants.STATUS_ACTIVE);
            featuredNewsRepository.save(featuredNews);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return featuredNewsDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public FeaturedNewsDTO updateFeaturedNews(FeaturedNewsDTO featuredNewsDTO, Long id) {
        validRequest(featuredNewsDTO);
        Optional<FeaturedNews> feN = featuredNewsRepository.findById(id);
        if (feN.isPresent()) {
            FeaturedNews featuredNews = feN.get();
            Optional<FeaturedNews> featuredNewsName = featuredNewsRepository.findByName(featuredNewsDTO.getName());
            if (featuredNewsName.isEmpty() || featuredNews.getId().equals(featuredNewsName.get().getId())) {
                featuredNews.setName(featuredNewsDTO.getName());
                featuredNews.setContent(featuredNewsDTO.getContent());
                featuredNews.setType(Constants.TYPE_FEATURED_NEWS);
                featuredNews.setTitle(featuredNewsDTO.getTitle());
                featuredNews.setSlug(featuredNewsDTO.getSlug());
                featuredNews.setAuthor(featuredNewsDTO.getAuthor());
                featuredNews.setStatus(Constants.STATUS_ACTIVE);
                featuredNewsRepository.save(featuredNews);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found by id: " + id);
        }
        return featuredNewsDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteFeaturedNews(Long id) {
        List<FeaturedNews> feN = featuredNewsRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(feN)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (FeaturedNews featuredNews : feN) {
            featuredNews.setStatus(Constants.STATUS_INACTIVE);
            featuredNews.setModifiedDate(new Date());
            featuredNewsRepository.save(featuredNews);
        }
    }

    @Override
    public Page<FeaturedNews> findAllFeaturedNews(Pageable pageable, FeaturedNewsDTO featuredNewsDTO) {
        String search;
        if (StringUtils.isEmpty(featuredNewsDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + featuredNewsDTO.getSearch().toLowerCase() + "%";
        }
        return featuredNewsRepository.findAllBySearch(pageable, search);
    }

    @SneakyThrows
    private void validRequest(FeaturedNewsDTO featuredNewsDTO) {
        if (featuredNewsDTO == null) {
            throw new MessageDescriptorFormatException("Request invalid");
        }

    }
}
