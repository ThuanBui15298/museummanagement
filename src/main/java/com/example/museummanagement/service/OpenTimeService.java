package com.example.museummanagement.service;

import com.example.museummanagement.entity.OpenTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface OpenTimeService {
    @Transactional
    OpenTime createOpentime(OpenTime openTime);

    @Transactional
    OpenTime updateOpenTime(OpenTime openTime, Long id);

    void deleteOpenTime(Long id);

    List<OpenTime> getAllOpenTime();
}
