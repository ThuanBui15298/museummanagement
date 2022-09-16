package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.ShowRegularlyDTO;
import com.example.museummanagement.entity.ShowRegularly;
import com.example.museummanagement.repository.ShowRegularlyRepository;
import com.example.museummanagement.service.ShowRegularlyService;
import com.example.museummanagement.ulti.Constants;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowRegularlyServiceImpl implements ShowRegularlyService {

    @Autowired
    private ShowRegularlyRepository showRegularlyRepository;

    @SneakyThrows
    @Override
    @Transactional
    public ShowRegularlyDTO createShowRegularly(ShowRegularlyDTO showRegularlyDTO) {
        Optional<ShowRegularly> optional = showRegularlyRepository.findByName(showRegularlyDTO.getName());
        ShowRegularly showRegular = new ShowRegularly();
        if (optional.isEmpty()) {
            showRegular.setName(showRegularlyDTO.getName());
            showRegular.setTitle(showRegularlyDTO.getTitle());
            showRegular.setContent(showRegularlyDTO.getContent());
            showRegular.setSlug(showRegularlyDTO.getSlug());
            showRegular.setAuthor(showRegularlyDTO.getAuthor());
            showRegular.setType(Constants.TYPE_SHOW_REGULARLY);
            showRegular.setStatus(Constants.STATUS_ACTIVE);
            showRegularlyRepository.save(showRegular);
        } else {
            throw new MessageDescriptorFormatException("Name da ton tai");
        }
        return showRegularlyDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public ShowRegularlyDTO updateShowRegularly(ShowRegularlyDTO showRegularlyDTO, Long id) {
        Optional<ShowRegularly> optionalShowRegularly = showRegularlyRepository.findById(id);
        ShowRegularly showRegular = optionalShowRegularly.get();
        if (optionalShowRegularly.isPresent()){
            Optional<ShowRegularly> optional = showRegularlyRepository.findByName(showRegularlyDTO.getName());
            if (optional.isEmpty()) {
                showRegular.setName(showRegularlyDTO.getName());
                showRegular.setTitle(showRegularlyDTO.getTitle());
                showRegular.setContent(showRegularlyDTO.getContent());
                showRegular.setSlug(showRegularlyDTO.getSlug());
                showRegular.setAuthor(showRegularlyDTO.getAuthor());
                showRegular.setType(Constants.TYPE_SHOW_REGULARLY);
                showRegular.setStatus(Constants.STATUS_ACTIVE);
                showRegularlyRepository.save(showRegular);
            } else {
                throw new MessageDescriptorFormatException("Name da ton tai");
            }
        } else {
            throw new MessageDescriptorFormatException("Id khong ton tai");
        }
        return showRegularlyDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void deleteShowRegularly(Long id) {
        List<ShowRegularly> showRegularlyList = showRegularlyRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(showRegularlyList)) {
            throw new MessageDescriptorFormatException(" Khong ton tai");
        }
        for (ShowRegularly showRegularly : showRegularlyList) {
            showRegularly.setStatus(Constants.STATUS_INACTIVE);
            showRegularly.setCreatDate(new Date());
            showRegularlyRepository.save(showRegularly);
        }
    }

    @Override
    public List<ShowRegularly> getAllShowRegularly() {
        return showRegularlyRepository.findAll();
    }
}
