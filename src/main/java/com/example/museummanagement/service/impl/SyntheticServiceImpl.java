package com.example.museummanagement.service.impl;

import com.example.museummanagement.entity.Synthetic;
import com.example.museummanagement.repository.SyntheticRepository;
import com.example.museummanagement.service.SyntheticService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SyntheticServiceImpl implements SyntheticService {


    private final SyntheticRepository syntheticRepository;

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
            synthetics.setStatus(Constants.STATUS_ACTIVE);
            syntheticRepository.save(synthetics);
        } else {
            throw new Exception("Name existed!");
        }
        return synthetic;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Synthetic updateSynthetic(Synthetic synthetic, Long id) {
        Optional<Synthetic> optionalSynthetic = syntheticRepository.findById(id);
        if (optionalSynthetic.isPresent()) {
            Synthetic sy = optionalSynthetic.get();

            Optional<Synthetic> optional = syntheticRepository.findByName(synthetic.getName());
            if (optional.isEmpty()
                    // so sánh 2 id với nhau nếu trùng thì bỏ qua
                    || sy.getId().equals(optional.get().getId())
                    // check name k dc nhỏ hơn hoặc bằng 0 kí tự
                    || optional.get().getName().length()>0){
                sy.setType(synthetic.getType());
                sy.setName(synthetic.getName());
                sy.setTitle(synthetic.getTitle());
                sy.setContent(synthetic.getContent());
                sy.setStatus(Constants.STATUS_ACTIVE);
                syntheticRepository.save(sy);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new NoSuchElementException();
        }
        return synthetic;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void deleteSynthetic(Long id) {
        List<Synthetic> syntheticList = syntheticRepository.findALlByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(syntheticList)) {
            throw new NoSuchElementException("Can not found!");
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
