package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.SightSeeingRuleDTO;
import com.example.museummanagement.entity.SightSeeingRule;
import com.example.museummanagement.repository.SightSeeingRuleRepository;
import com.example.museummanagement.service.SightSeeingRuleService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SightSeeingRuleServiceImpl implements SightSeeingRuleService {

    private final SightSeeingRuleRepository sightSeeingRuleRepository;

    @SneakyThrows
    @Override
    public SightSeeingRuleDTO createSightSeeingRule(SightSeeingRuleDTO sightSeeingRuleDTO) {
        Optional<SightSeeingRule> optionalSightSeeingRule = sightSeeingRuleRepository.findByName(sightSeeingRuleDTO.getName());
        SightSeeingRule sightSeeingRule = new SightSeeingRule();
        if (optionalSightSeeingRule.isEmpty()) {
            sightSeeingRule.setName(sightSeeingRuleDTO.getName());
            sightSeeingRule.setTitle(sightSeeingRuleDTO.getTitle());
            sightSeeingRule.setContent(sightSeeingRuleDTO.getContent());
            sightSeeingRule.setType(Constants.TYPE_SIGHTSEEING_RULE);
            sightSeeingRule.setStatus(Constants.STATUS_ACTIVE);
            sightSeeingRuleRepository.save(sightSeeingRule);
        } else {
            throw new Exception("Name existed!");
        }
        return sightSeeingRuleDTO;
    }

    @SneakyThrows
    @Override
    public SightSeeingRuleDTO updateSightSeeingRule(SightSeeingRuleDTO sightSeeingRuleDTO, Long id) {
        Optional<SightSeeingRule> optionalSightSeeingRule = sightSeeingRuleRepository.findById(id);
        if (optionalSightSeeingRule.isPresent()) {
            SightSeeingRule sightSeeingRule = optionalSightSeeingRule.get();
            Optional<SightSeeingRule> optional = sightSeeingRuleRepository.findByName(sightSeeingRuleDTO.getName());
            if(optional.isEmpty() || sightSeeingRule.getId().equals(optional.get().getId())) {
                sightSeeingRule.setName(sightSeeingRuleDTO.getName());
                sightSeeingRule.setTitle(sightSeeingRuleDTO.getTitle());
                sightSeeingRule.setContent(sightSeeingRuleDTO.getContent());
                sightSeeingRule.setType(Constants.TYPE_SIGHTSEEING_RULE);
                sightSeeingRule.setStatus(Constants.STATUS_ACTIVE);
                sightSeeingRuleRepository.save(sightSeeingRule);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found by id: " + id);
        }
        return sightSeeingRuleDTO;
    }

    @SneakyThrows
    @Override
    public void deleteSightSeeingRule(Long id) {
        List<SightSeeingRule> sightSeeingRuleList = sightSeeingRuleRepository.findAllByIdAndStatus(id,Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(sightSeeingRuleList)) {
            throw new Exception("Can not found!");
        }
        for (SightSeeingRule sightSeeingRule: sightSeeingRuleList) {
            sightSeeingRule.setStatus(Constants.STATUS_INACTIVE);
            sightSeeingRuleRepository.save(sightSeeingRule);
        }
    }

    @Override
    public List<SightSeeingRule> getAllSightSeeingRule() {
        return sightSeeingRuleRepository.findAll();
    }
}
