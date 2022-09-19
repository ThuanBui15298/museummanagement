package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CollectingWorkDTO;
import com.example.museummanagement.entity.CollectingWork;
import com.example.museummanagement.repository.CollectingWorkRepository;
import com.example.museummanagement.service.CollectingWorkService;
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
            throw new Exception("Name existed!");
        }
        return collectingWorkDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public CollectingWorkDTO updateCollectingWork(CollectingWorkDTO collectingWorkDTO, Long id) {
        Optional<CollectingWork> optionalCollectingWork = collectingWorkRepository.findById(id);
        CollectingWork collectingWork = optionalCollectingWork.get();
        if (optionalCollectingWork.isPresent()) {
            Optional<CollectingWork> collectingWorks = collectingWorkRepository.findByName(collectingWorkDTO.getName());
            if (collectingWorks.isEmpty()){
                collectingWork.setType(Constants.TYPE_COLLECTING_WORK);
                collectingWork.setName(collectingWorkDTO.getName());
                collectingWork.setTitle(collectingWorkDTO.getTitle());
                collectingWork.setSlug(collectingWorkDTO.getSlug());
                collectingWork.setContent(collectingWorkDTO.getContent());
                collectingWork.setAuthor(collectingWorkDTO.getAuthor());
                collectingWork.setStatus(Constants.STATUS_ACTIVE);
                collectingWorkRepository.save(collectingWork);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found id: " + id);
        }
        return collectingWorkDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteCollectingWork(Long id) {
        List<CollectingWork> collectingWorks = collectingWorkRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(collectingWorks)) {
            throw new MessageDescriptorFormatException("No existed");
        }
        for (CollectingWork collectingWork : collectingWorks) {
            collectingWork.setStatus(Constants.STATUS_INACTIVE);
            collectingWork.setModifiedDate(new Date());
            collectingWorkRepository.save(collectingWork);
        }
    }

    @Override
    public List<CollectingWork> findAllCollectingWork() {
        return collectingWorkRepository.findAll();
    }
}
