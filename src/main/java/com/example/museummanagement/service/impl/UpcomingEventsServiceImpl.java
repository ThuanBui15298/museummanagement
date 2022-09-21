package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.UpcomingEventsDTO;
import com.example.museummanagement.entity.UpcomingEvents;
import com.example.museummanagement.repository.UpcomingEventsRepository;
import com.example.museummanagement.service.UpcomingEventsService;
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
public class UpcomingEventsServiceImpl implements UpcomingEventsService {

    private final UpcomingEventsRepository upcomingEventsRepository;

    @SneakyThrows
    @Transactional
    @Override
    public UpcomingEventsDTO createUpcomingEvents(UpcomingEventsDTO upcomingEventsDTO) {
        validRequest(upcomingEventsDTO);
        Optional<UpcomingEvents> upE = upcomingEventsRepository.findByName(upcomingEventsDTO.getName());
        UpcomingEvents upcomingEvents = new UpcomingEvents();
        if (upE.isEmpty()) {
            upcomingEvents.setName(upcomingEventsDTO.getName());
            upcomingEvents.setContent(upcomingEventsDTO.getContent());
            upcomingEvents.setType(Constants.TYPE_UPCOMING_EVENTS);
            upcomingEvents.setTitle(upcomingEventsDTO.getTitle());
            upcomingEvents.setSlug(upcomingEventsDTO.getSlug());
            upcomingEvents.setSlug(upcomingEventsDTO.getAuthor());
            upcomingEvents.setStatus(Constants.STATUS_ACTIVE);
            upcomingEventsRepository.save(upcomingEvents);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return upcomingEventsDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public UpcomingEventsDTO updateUpcomingEvents(UpcomingEventsDTO upcomingEventsDTO, Long id) {
        validRequest(upcomingEventsDTO);
        Optional<UpcomingEvents> upE = upcomingEventsRepository.findById(id);
        if (upE.isPresent()) {
            UpcomingEvents upcomingEvents = upE.get();

            Optional<UpcomingEvents> upcomingEventsName = upcomingEventsRepository.findByName(upcomingEventsDTO.getName());
            if (upcomingEventsName.isEmpty() || upcomingEvents.getId().equals(upcomingEventsName.get().getId())) {
                upcomingEvents.setName(upcomingEventsDTO.getName());
                upcomingEvents.setContent(upcomingEventsDTO.getContent());
                upcomingEvents.setType(Constants.TYPE_UPCOMING_EVENTS);
                upcomingEvents.setTitle(upcomingEventsDTO.getTitle());
                upcomingEvents.setSlug(upcomingEventsDTO.getSlug());
                upcomingEvents.setAuthor(upcomingEventsDTO.getAuthor());
                upcomingEvents.setStatus(Constants.STATUS_ACTIVE);
                upcomingEventsRepository.save(upcomingEvents);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found by id: " + id);
        }
        return upcomingEventsDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteUpcomingEvents(Long id) {
        List<UpcomingEvents> upE = upcomingEventsRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(upE)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (UpcomingEvents upcomingEvents : upE) {
            upcomingEvents.setStatus(Constants.STATUS_INACTIVE);
            upcomingEvents.setModifiedDate(new Date());
            upcomingEventsRepository.save(upcomingEvents);
        }
    }

    @Override
    public Iterable<UpcomingEvents> findAll() {
        return upcomingEventsRepository.findAll();
    }

    @SneakyThrows
    private void validRequest(UpcomingEventsDTO upcomingEventsDTO) {
        if (upcomingEventsDTO == null) {
            throw new MessageDescriptorFormatException("Request invalid");
        }

    }
}
