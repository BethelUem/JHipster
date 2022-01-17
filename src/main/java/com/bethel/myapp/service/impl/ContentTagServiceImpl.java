package com.bethel.myapp.service.impl;

import com.bethel.myapp.domain.ContentTag;
import com.bethel.myapp.repository.ContentTagRepository;
import com.bethel.myapp.service.ContentTagService;
import com.bethel.myapp.service.dto.ContentTagDTO;
import com.bethel.myapp.service.mapper.ContentTagMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContentTag}.
 */
@Service
@Transactional
public class ContentTagServiceImpl implements ContentTagService {

    private final Logger log = LoggerFactory.getLogger(ContentTagServiceImpl.class);

    private final ContentTagRepository contentTagRepository;

    private final ContentTagMapper contentTagMapper;

    public ContentTagServiceImpl(ContentTagRepository contentTagRepository, ContentTagMapper contentTagMapper) {
        this.contentTagRepository = contentTagRepository;
        this.contentTagMapper = contentTagMapper;
    }

    @Override
    public ContentTagDTO save(ContentTagDTO contentTagDTO) {
        log.debug("Request to save ContentTag : {}", contentTagDTO);
        ContentTag contentTag = contentTagMapper.toEntity(contentTagDTO);
        contentTag = contentTagRepository.save(contentTag);
        return contentTagMapper.toDto(contentTag);
    }

    @Override
    public Optional<ContentTagDTO> partialUpdate(ContentTagDTO contentTagDTO) {
        log.debug("Request to partially update ContentTag : {}", contentTagDTO);

        return contentTagRepository
            .findById(contentTagDTO.getId())
            .map(existingContentTag -> {
                contentTagMapper.partialUpdate(existingContentTag, contentTagDTO);

                return existingContentTag;
            })
            .map(contentTagRepository::save)
            .map(contentTagMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContentTagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContentTags");
        return contentTagRepository.findAll(pageable).map(contentTagMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContentTagDTO> findOne(Long id) {
        log.debug("Request to get ContentTag : {}", id);
        return contentTagRepository.findById(id).map(contentTagMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContentTag : {}", id);
        contentTagRepository.deleteById(id);
    }
}
