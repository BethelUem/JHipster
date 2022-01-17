package com.bethel.myapp.service.impl;

import com.bethel.myapp.domain.Catalog;
import com.bethel.myapp.repository.CatalogRepository;
import com.bethel.myapp.service.CatalogService;
import com.bethel.myapp.service.dto.CatalogDTO;
import com.bethel.myapp.service.mapper.CatalogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Catalog}.
 */
@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    private final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final CatalogRepository catalogRepository;

    private final CatalogMapper catalogMapper;

    public CatalogServiceImpl(CatalogRepository catalogRepository, CatalogMapper catalogMapper) {
        this.catalogRepository = catalogRepository;
        this.catalogMapper = catalogMapper;
    }

    @Override
    public CatalogDTO save(CatalogDTO catalogDTO) {
        log.debug("Request to save Catalog : {}", catalogDTO);
        Catalog catalog = catalogMapper.toEntity(catalogDTO);
        catalog = catalogRepository.save(catalog);
        return catalogMapper.toDto(catalog);
    }

    @Override
    public Optional<CatalogDTO> partialUpdate(CatalogDTO catalogDTO) {
        log.debug("Request to partially update Catalog : {}", catalogDTO);

        return catalogRepository
            .findById(catalogDTO.getId())
            .map(existingCatalog -> {
                catalogMapper.partialUpdate(existingCatalog, catalogDTO);

                return existingCatalog;
            })
            .map(catalogRepository::save)
            .map(catalogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CatalogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Catalogs");
        return catalogRepository.findAll(pageable).map(catalogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CatalogDTO> findOne(Long id) {
        log.debug("Request to get Catalog : {}", id);
        return catalogRepository.findById(id).map(catalogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Catalog : {}", id);
        catalogRepository.deleteById(id);
    }
}
