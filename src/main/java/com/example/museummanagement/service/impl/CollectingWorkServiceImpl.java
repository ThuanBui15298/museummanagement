package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CollectingWorkDTO;
import com.example.museummanagement.entity.CollectingWork;
import com.example.museummanagement.repository.CollectingWorkRepository;
import com.example.museummanagement.service.CollectingWorkService;
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
public class CollectingWorkServiceImpl implements CollectingWorkService {

    private final CollectingWorkRepository collectingWorkRepository;

    @SneakyThrows
    @Transactional
    @Override
    public CollectingWorkDTO createCollectingWork(CollectingWorkDTO collectingWorkDTO) {
        Optional<CollectingWork> optionalCollectingWork = collectingWorkRepository.findByName(collectingWorkDTO.getName());
        CollectingWork collectingWork = new CollectingWork();
        if (optionalCollectingWork.isEmpty()){
            collectingWork.setType(Constants.TYPE_COLLECTING_WORK);
            collectingWork.setName(collectingWorkDTO.getName());
            collectingWork.setTitle(collectingWorkDTO.getTitle());
            collectingWork.setSlug(collectingWorkDTO.getSlug());
            collectingWork.setContent(collectingWorkDTO.getContent());
            collectingWork.setAuthor(collectingWorkDTO.getAuthor());
            collectingWork.setStatus(Constants.STATUS_ACTIVE);
            collectingWorkRepository.save(collectingWork);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return collectingWorkDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public CollectingWorkDTO updateCollectingWork(CollectingWorkDTO collectingWorkDTO, Long id) {
        Optional<CollectingWork> optionalCollectingWork = collectingWorkRepository.findById(id);
        if (optionalCollectingWork.isPresent()) {
            CollectingWork collectingWork = optionalCollectingWork.get();
            Optional<CollectingWork> collectingWorks = collectingWorkRepository.findByName(collectingWorkDTO.getName());
            if (collectingWorks.isEmpty() || collectingWork.getId().equals(collectingWorks.get().getId())){
                collectingWork.setType(Constants.TYPE_COLLECTING_WORK);
                collectingWork.setName(collectingWorkDTO.getName());
                collectingWork.setTitle(collectingWorkDTO.getTitle());
                collectingWork.setSlug(collectingWorkDTO.getSlug());
                collectingWork.setContent(collectingWorkDTO.getContent());
                collectingWork.setAuthor(collectingWorkDTO.getAuthor());
                collectingWork.setStatus(Constants.STATUS_ACTIVE);
                collectingWorkRepository.save(collectingWork);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found id: " + id);
        }
        return collectingWorkDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteCollectingWork(Long id) {
        List<CollectingWork> collectingWorks = collectingWorkRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(collectingWorks)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (CollectingWork collectingWork : collectingWorks) {
            collectingWork.setStatus(Constants.STATUS_INACTIVE);
            collectingWork.setModifiedDate(new Date());
            collectingWorkRepository.save(collectingWork);
        }
    }

    @Override
    public Page<CollectingWork> findAllCollectingWork(Pageable pageable, CollectingWorkDTO collectingWorkDTO) {
        String search;
        if (StringUtils.isEmpty(collectingWorkDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + collectingWorkDTO.getSearch().toLowerCase() + "%";
        }
        return collectingWorkRepository.findAllBySearch(pageable,search);
    }
}
