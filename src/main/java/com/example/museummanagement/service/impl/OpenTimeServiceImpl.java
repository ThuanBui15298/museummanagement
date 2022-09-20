package com.example.museummanagement.service.impl;

import com.example.museummanagement.entity.OpenTime;
import com.example.museummanagement.repository.OpenTimeRepository;
import com.example.museummanagement.service.OpenTimeService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenTimeServiceImpl implements OpenTimeService {

    private final OpenTimeRepository openTimeRepository;

    @SneakyThrows
    @Override
    public OpenTime createOpentime(OpenTime openTime) {
        Optional<OpenTime> optionalOpenTime = openTimeRepository.findByName(openTime.getName());
        OpenTime open = new OpenTime();
        if (optionalOpenTime.isEmpty()) {
            open.setName(openTime.getName());
            open.setTitle(openTime.getTitle());
            open.setContent(openTime.getContent());
            open.setType(Constants.TYPE_OPEN_TIME);
            open.setStatus(Constants.STATUS_ACTIVE);
            openTimeRepository.save(open);
        } else {
            throw new Exception("Name existed!");
        }
         return openTime;
    }

    @SneakyThrows
    @Override
    public OpenTime updateOpenTime(OpenTime openTime, Long id) {
        Optional<OpenTime> optionalOpenTime = openTimeRepository.findById(id);
        if (optionalOpenTime.isPresent()) {
            OpenTime open = optionalOpenTime.get();
            Optional<OpenTime> optional = openTimeRepository.findByName(openTime.getName());
            if (optional.isEmpty()) {
                open.setName(openTime.getName());
                open.setTitle(openTime.getTitle());
                open.setContent(openTime.getContent());
                open.setType(Constants.TYPE_OPEN_TIME);
                open.setStatus(Constants.STATUS_ACTIVE);
                openTimeRepository.save(open);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found by id: " + id);
        }

        return openTime;
    }

    @SneakyThrows
    @Override
    public void deleteOpenTime(Long id) {
        List<OpenTime> openTimeList = openTimeRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(openTimeList)) {
            throw new Exception("Can not found!");
        }

        for (OpenTime openTime: openTimeList) {
            openTime.setStatus(Constants.STATUS_INACTIVE);
            openTimeRepository.save(openTime);
        }
    }

    @Override
    public List<OpenTime> getAllOpenTime() {
        return openTimeRepository.findAll();
    }
}
