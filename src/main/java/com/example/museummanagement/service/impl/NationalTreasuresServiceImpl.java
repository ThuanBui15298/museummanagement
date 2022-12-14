package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.NationalTreasuresDTO;
import com.example.museummanagement.entity.NationalTreasures;
import com.example.museummanagement.repository.NationalTreasuresRepository;
import com.example.museummanagement.service.NationalTreasuresService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NationalTreasuresServiceImpl implements NationalTreasuresService {

    private final NationalTreasuresRepository nationalTreasuresRepository;

    @SneakyThrows
    @Transactional
    @Override
    public NationalTreasuresDTO createNationalTreasure(NationalTreasuresDTO nationalTreasuresDTO) {
        Optional<NationalTreasures> optionalNationalTreasures = nationalTreasuresRepository.findByName(nationalTreasuresDTO.getName());
        NationalTreasures nationalTreasures = new NationalTreasures();
        if (optionalNationalTreasures.isEmpty()) {
            nationalTreasures.setName(nationalTreasuresDTO.getName());
            nationalTreasures.setTitle(nationalTreasuresDTO.getTitle());
            nationalTreasures.setContent(nationalTreasuresDTO.getContent());
            nationalTreasures.setSlug(nationalTreasuresDTO.getSlug());
            nationalTreasures.setAuthor(nationalTreasuresDTO.getAuthor());
            nationalTreasures.setType(Constants.TYPE_NATIONAL_TREASURES);
            nationalTreasures.setStatus(Constants.STATUS_ACTIVE);
            nationalTreasuresRepository.save(nationalTreasures);
        } else {
            throw new Exception("Name existed!");
        }
        return nationalTreasuresDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public NationalTreasuresDTO updateNationalTreasure(NationalTreasuresDTO nationalTreasuresDTO, Long id) {
        Optional<NationalTreasures> optionalNationalTreasures = nationalTreasuresRepository.findById(id);
        if (optionalNationalTreasures.isPresent()) {
            NationalTreasures nationalTreasures = optionalNationalTreasures.get();
            Optional<NationalTreasures> optional = nationalTreasuresRepository.findByName(nationalTreasuresDTO.getName());
            if (optional.isEmpty() || nationalTreasures.getId().equals(optional.get().getId())) {
                nationalTreasures.setName(nationalTreasuresDTO.getName());
                nationalTreasures.setTitle(nationalTreasuresDTO.getTitle());
                nationalTreasures.setContent(nationalTreasuresDTO.getContent());
                nationalTreasures.setSlug(nationalTreasuresDTO.getSlug());
                nationalTreasures.setAuthor(nationalTreasuresDTO.getAuthor());
                nationalTreasures.setType(Constants.TYPE_NATIONAL_TREASURES);
                nationalTreasures.setStatus(Constants.STATUS_ACTIVE);
                nationalTreasuresRepository.save(nationalTreasures);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found by id: !" + id);
        }

        return nationalTreasuresDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteNationalTreasure(Long id) {
        List<NationalTreasures> nationalTreasuresList = nationalTreasuresRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(nationalTreasuresList)) {
            throw new Exception("Can not found!");
        }

        for (NationalTreasures nationalTreasures : nationalTreasuresList) {
            nationalTreasures.setStatus(Constants.STATUS_INACTIVE);
            nationalTreasuresRepository.save(nationalTreasures);
        }
    }

    @Override
    public Page<NationalTreasures> findAllNationalTreasures(Pageable pageable, NationalTreasuresDTO nationalTreasuresDTO) {
        String search;
        if (StringUtils.isEmpty(nationalTreasuresDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + nationalTreasuresDTO.getSearch().toLowerCase() + "%";
        }
        return nationalTreasuresRepository.findAllBySearch(pageable, search);
    }
}