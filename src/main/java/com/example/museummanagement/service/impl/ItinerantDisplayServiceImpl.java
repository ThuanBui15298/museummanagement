package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.ItinerantDisplayDTO;
import com.example.museummanagement.entity.ItinerantDisplay;
import com.example.museummanagement.repository.ItinerantDisplayRepository;
import com.example.museummanagement.service.ItinerantDisplayService;
import com.example.museummanagement.ulti.Constants;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItinerantDisplayServiceImpl implements ItinerantDisplayService {

    @Autowired
    private ItinerantDisplayRepository itinerantDisplayRepository;

    @SneakyThrows
    @Transactional
    @Override
    public ItinerantDisplayDTO createItinerantDisplay(ItinerantDisplayDTO itinerantDisplayDTO) {
        Optional<ItinerantDisplay> optionalItinerantDisplay = itinerantDisplayRepository.findByName(itinerantDisplayDTO.getName());
        ItinerantDisplay itinerant = new ItinerantDisplay();
        if (optionalItinerantDisplay.isEmpty()){
            itinerant.setType(Constants.TYPE_ITINERANT_DISPLAY);
            itinerant.setName(itinerantDisplayDTO.getName());
            itinerant.setTitle(itinerantDisplayDTO.getTitle());
            itinerant.setSlug(itinerantDisplayDTO.getSlug());
            itinerant.setDescription(itinerantDisplayDTO.getDescription());
            itinerant.setContent(itinerantDisplayDTO.getContent());
            itinerant.setAuthor(itinerantDisplayDTO.getAuthor());
            itinerant.setStatus(Constants.STATUS_ACTIVE);
            itinerantDisplayRepository.save(itinerant);
        } else {
            throw new Exception("Name existed!");
        }
        return itinerantDisplayDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public ItinerantDisplayDTO updateItinerantDisplay(Long id, ItinerantDisplayDTO itinerantDisplayDTO) {
        Optional<ItinerantDisplay> optionalItinerantDisplay = itinerantDisplayRepository.findById(id);
        if (optionalItinerantDisplay.isPresent()) {
            ItinerantDisplay itinerant = optionalItinerantDisplay.get();
            Optional<ItinerantDisplay> optional = itinerantDisplayRepository.findByName(itinerantDisplayDTO.getName());
            if (optional.isEmpty() || itinerant.getId().equals(optional.get().getId())) {
                itinerant.setType(Constants.TYPE_ITINERANT_DISPLAY);
                itinerant.setName(itinerantDisplayDTO.getName());
                itinerant.setTitle(itinerantDisplayDTO.getTitle());
                itinerant.setSlug(itinerantDisplayDTO.getSlug());
                itinerant.setDescription(itinerantDisplayDTO.getDescription());
                itinerant.setContent(itinerantDisplayDTO.getContent());
                itinerant.setAuthor(itinerantDisplayDTO.getAuthor());
                itinerant.setStatus(Constants.STATUS_ACTIVE);
                itinerantDisplayRepository.save(itinerant);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found with id: !" + id);
        }
        return itinerantDisplayDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteItinerantDisplay(Long id) {
        List<ItinerantDisplay> itinerantDisplayList = itinerantDisplayRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(itinerantDisplayList)) {
            throw new Exception("Can not found!");
        }
        for (ItinerantDisplay itinerantDisplay: itinerantDisplayList) {
            itinerantDisplay.setStatus(Constants.STATUS_INACTIVE);
            itinerantDisplay.setCreatDate(new Date());
            itinerantDisplayRepository.save(itinerantDisplay);
        }
    }

    @Override
    public List<ItinerantDisplay> getAllItinerantDisplay() {
        return itinerantDisplayRepository.findAll();
    }
}
