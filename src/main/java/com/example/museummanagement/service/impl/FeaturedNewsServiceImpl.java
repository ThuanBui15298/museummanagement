package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.NewsDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.News;
import com.example.museummanagement.repository.FeaturedNewsRepository;
import com.example.museummanagement.repository.NewsRepository;
import com.example.museummanagement.service.FeaturedNewsService;
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
public class FeaturedNewsServiceImpl implements FeaturedNewsService {

    private final FeaturedNewsRepository featuredNewsRepository;

    @SneakyThrows
    @Transactional
    @Override
    public FeaturedNewsDTO createFeaturedNews(FeaturedNewsDTO featuredNewsDTO) throws Exception {
        validRequest(featuredNewsDTO);
        Optional<FeaturedNews> feN = featuredNewsRepository.findByName(featuredNewsDTO.getName());
        FeaturedNews featuredNews = new FeaturedNews();
        if (feN.isEmpty()) {
            featuredNews.setName(featuredNewsDTO.getName());
            featuredNews.setContent(featuredNewsDTO.getContent());
            featuredNews.setDescription(featuredNewsDTO.getDescription());
            featuredNews.setType(Constants.TYPE_FEATURED_NEWS);
            featuredNews.setTitle(featuredNewsDTO.getTitle());
            featuredNews.setSlug(featuredNewsDTO.getSlug());
            featuredNews.setStatus(Constants.STATUS_ACTIVE);
            featuredNewsRepository.save(featuredNews);
        } else {
            throw new MessageDescriptorFormatException("Name da ton tai");
        }
        return featuredNewsDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public FeaturedNewsDTO updateFeaturedNews(FeaturedNewsDTO featuredNewsDTO, Long id) {
        validRequest(featuredNewsDTO);
        Optional<FeaturedNews> feN = featuredNewsRepository.findById(id);
        FeaturedNews featuredNews = feN.get();
        if (feN.isPresent()) {
            Optional<FeaturedNews> featuredNewsOptional = featuredNewsRepository.findByName(featuredNewsDTO.getName());
            if (featuredNewsOptional.isEmpty()) {
                featuredNews.setName(featuredNewsDTO.getName());
                featuredNews.setContent(featuredNewsDTO.getContent());
                featuredNews.setDescription(featuredNewsDTO.getDescription());
                featuredNews.setType(Constants.TYPE_FEATURED_NEWS);
                featuredNews.setTitle(featuredNewsDTO.getTitle());
                featuredNews.setSlug(featuredNewsDTO.getSlug());
                featuredNews.setStatus(Constants.STATUS_ACTIVE);
                featuredNewsRepository.save(featuredNews);
            } else {
                throw new MessageDescriptorFormatException("Name muc da ton tai");
            }
        } else {
            throw new MessageDescriptorFormatException("Id khong ton tai");
        }
        return featuredNewsDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteFeaturedNews(Long id) {
        List<FeaturedNews> feN = featuredNewsRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(feN)) {
            throw new MessageDescriptorFormatException("Khong ton tai");
        }
        for (FeaturedNews featuredNews : feN) {
            featuredNews.setStatus(Constants.STATUS_INACTIVE);
            featuredNews.setModifiedDate(new Date());
            featuredNewsRepository.save(featuredNews);
        }
    }

    @Override
    public Iterable<FeaturedNews> findAll() {

        return featuredNewsRepository.findAll();
    }

    @SneakyThrows
    private void validRequest(FeaturedNewsDTO featuredNewsDTO) {
        if (featuredNewsDTO == null) {
            throw new MessageDescriptorFormatException("Request invalid");
        }

    }
}
