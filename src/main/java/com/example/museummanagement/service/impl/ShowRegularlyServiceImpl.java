package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.ShowRegularlyDTO;
import com.example.museummanagement.entity.ShowRegularly;
import com.example.museummanagement.repository.ShowRegularlyRepository;
import com.example.museummanagement.service.ShowRegularlyService;
import com.example.museummanagement.ulti.Constants;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
        if (optionalShowRegularly.isPresent()){
            ShowRegularly showRegular = optionalShowRegularly.get();

            Optional<ShowRegularly> optional = showRegularlyRepository.findByName(showRegularlyDTO.getName());
            if (optional.isEmpty() || showRegular.getId().equals(optional.get().getId())) {
                showRegular.setName(showRegularlyDTO.getName());
                showRegular.setTitle(showRegularlyDTO.getTitle());
                showRegular.setContent(showRegularlyDTO.getContent());
                showRegular.setSlug(showRegularlyDTO.getSlug());
                showRegular.setAuthor(showRegularlyDTO.getAuthor());
                showRegular.setType(Constants.TYPE_SHOW_REGULARLY);
                showRegular.setStatus(Constants.STATUS_ACTIVE);
                showRegularlyRepository.save(showRegular);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found by id: " + id);
        }
        return showRegularlyDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void deleteShowRegularly(Long id) {
        List<ShowRegularly> showRegularlyList = showRegularlyRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(showRegularlyList)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (ShowRegularly showRegularly : showRegularlyList) {
            showRegularly.setStatus(Constants.STATUS_INACTIVE);
            showRegularly.setCreatDate(new Date());
            showRegularlyRepository.save(showRegularly);
        }
    }

    @Override
    public Page<ShowRegularly> findAllShowRegularly(Pageable pageable, ShowRegularlyDTO showRegularlyDTO) {
        String search;
        if (StringUtils.isEmpty(showRegularlyDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + showRegularlyDTO.getSearch().toLowerCase() + "%";
        }
        return showRegularlyRepository.findAllBySearch(pageable, search);
    }
}
