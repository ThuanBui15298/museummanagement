package com.example.museummanagement.service;



import com.example.museummanagement.dto.CollectingWorkDTO;
import com.example.museummanagement.entity.CollectingWork;

import javax.transaction.Transactional;
import java.util.List;

public interface CollectingWorkService {

    @Transactional
    CollectingWorkDTO createCollectingWork(CollectingWorkDTO collectingWorkDTO);

    @Transactional
    CollectingWorkDTO updateCollectingWork(CollectingWorkDTO collectingWorkDTO, Long id)  ;

    void deleteCollectingWork(Long id) ;

    List<CollectingWork> findAllCollectingWork();
}
