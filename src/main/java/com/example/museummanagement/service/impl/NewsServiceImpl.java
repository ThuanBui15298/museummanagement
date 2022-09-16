package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.NewsDTO;
import com.example.museummanagement.entity.News;
import com.example.museummanagement.repository.NewsRepository;
import com.example.museummanagement.service.NewsService;
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
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Transactional
    @Override
    public NewsDTO createNews(NewsDTO newsDTO) throws Exception {
        validRequest(newsDTO);
        Optional<News> ne = newsRepository.findByName(newsDTO.getName());
            News news = new News();
        if (ne.isEmpty()) {
            news.setName(newsDTO.getName());
            news.setContent(newsDTO.getContent());
            news.setDescription(newsDTO.getDescription());
            news.setType(newsDTO.getType());
            news.setTitle(newsDTO.getTitle());
            news.setSlug(newsDTO.getSlug());
            news.setStatus(Constants.STATUS_ACTIVE);
            newsRepository.save(news);
        } else {
            throw new MessageDescriptorFormatException("Name da ton tai");
        }
        return newsDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public NewsDTO updateNews(NewsDTO newsDTO, Long id) {
        validRequest(newsDTO);
        Optional<News> news = newsRepository.findById(id);
        News ne = news.get();
        if (news.isPresent()) {
            Optional<News> newsName = newsRepository.findByName(newsDTO.getName());
            if (newsName.isEmpty()) {
                ne.setName(newsDTO.getName());
                ne.setContent(newsDTO.getContent());
                ne.setDescription(newsDTO.getDescription());
                ne.setType(newsDTO.getType());
                ne.setTitle(newsDTO.getTitle());
                ne.setSlug(newsDTO.getSlug());
                ne.setStatus(Constants.STATUS_ACTIVE);
                newsRepository.save(ne);
            } else {
                throw new MessageDescriptorFormatException("Name muc da ton tai");
            }
        } else {
            throw new MessageDescriptorFormatException("Id khong ton tai");
        }
        return newsDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteNews(Long id) {
        List<News> ne = newsRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(ne)) {
            throw new MessageDescriptorFormatException("Khong ton tai");
        }
        for (News news : ne) {
            news.setStatus(Constants.STATUS_INACTIVE);
            news.setModifiedDate(new Date());
            newsRepository.save(news);
        }
    }

    @Override
    public Iterable<News> findAll() {
        return newsRepository.findAll();
    }

    @SneakyThrows
    private void validRequest(NewsDTO newsDTO) {
        if (newsDTO == null) {
            throw new MessageDescriptorFormatException("Request invalid");
        }

    }
}
