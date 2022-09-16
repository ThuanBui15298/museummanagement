package com.example.museummanagement.service.impl;

import com.example.museummanagement.entity.Synthetic;
import com.example.museummanagement.exception.ExistedNameException;
import com.example.museummanagement.repository.SyntheticRepository;
import com.example.museummanagement.service.SyntheticService;
import com.example.museummanagement.ulti.Constants;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SyntheticServiceImpl implements SyntheticService {

    @Autowired
    private SyntheticRepository syntheticRepository;

    @SneakyThrows
    @Override
    @Transactional
    public Synthetic createSynthetic(Synthetic synthetic) {
        Optional<Synthetic> optionalSynthetic = syntheticRepository.findByName(synthetic.getName());
        Synthetic synthetics = new Synthetic();
        if (optionalSynthetic.isEmpty()) {
            synthetics.setType(synthetic.getType());
            synthetics.setName(synthetic.getName());
            synthetics.setTitle(synthetic.getTitle());
            synthetics.setContent(synthetic.getContent());
            synthetics.setCategoryId(synthetic.getCategoryId());
            synthetics.setStatus(Constants.STATUS_ACTIVE);
            syntheticRepository.save(synthetics);
        } else {
            throw new ExistedNameException();
        }
        return synthetic;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Synthetic updateSynthetic(Synthetic synthetic, Long id) {
        Optional<Synthetic> optionalSynthetic = syntheticRepository.findById(id);
        Synthetic synthetic1 = optionalSynthetic.get();
        if (optionalSynthetic.isPresent()) {
            Optional<Synthetic> optional = syntheticRepository.findByName(synthetic.getName());
            if (optional.isEmpty()){
                synthetic1.setType(synthetic.getType());
                synthetic1.setName(synthetic.getName());
                synthetic1.setTitle(synthetic.getTitle());
                synthetic1.setContent(synthetic.getContent());
                synthetic1.setCategoryId(synthetic.getCategoryId());
                synthetic1.setStatus(Constants.STATUS_ACTIVE);
                syntheticRepository.save(synthetic1);
            } else {
                throw new ExistedNameException();
            }
        } else {
            throw new NoSuchElementException();
        }
        return synthetic;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void deteleSynthetic(Long id) {
        List<Synthetic> syntheticList = syntheticRepository.findALlByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(syntheticList)) {
            throw new NoSuchElementException("Not found synthetic");
        }
        for (Synthetic synthetic: syntheticList) {
            synthetic.setCreatDate(new Date());
            synthetic.setStatus(Constants.STATUS_INACTIVE);
            syntheticRepository.save(synthetic);
        }
    }

    @Override
    public List<Synthetic> getAllSynthetic() {
        return syntheticRepository.findAll();
    }
}
