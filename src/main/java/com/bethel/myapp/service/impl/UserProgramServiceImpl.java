package com.bethel.myapp.service.impl;

import com.bethel.myapp.domain.UserProgram;
import com.bethel.myapp.repository.UserProgramRepository;
import com.bethel.myapp.service.UserProgramService;
import com.bethel.myapp.service.dto.UserProgramDTO;
import com.bethel.myapp.service.mapper.UserProgramMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserProgram}.
 */
@Service
@Transactional
public class UserProgramServiceImpl implements UserProgramService {

    private final Logger log = LoggerFactory.getLogger(UserProgramServiceImpl.class);

    private final UserProgramRepository userProgramRepository;

    private final UserProgramMapper userProgramMapper;

    public UserProgramServiceImpl(UserProgramRepository userProgramRepository, UserProgramMapper userProgramMapper) {
        this.userProgramRepository = userProgramRepository;
        this.userProgramMapper = userProgramMapper;
    }

    @Override
    public UserProgramDTO save(UserProgramDTO userProgramDTO) {
        log.debug("Request to save UserProgram : {}", userProgramDTO);
        UserProgram userProgram = userProgramMapper.toEntity(userProgramDTO);
        userProgram = userProgramRepository.save(userProgram);
        return userProgramMapper.toDto(userProgram);
    }

    @Override
    public Optional<UserProgramDTO> partialUpdate(UserProgramDTO userProgramDTO) {
        log.debug("Request to partially update UserProgram : {}", userProgramDTO);

        return userProgramRepository
            .findById(userProgramDTO.getId())
            .map(existingUserProgram -> {
                userProgramMapper.partialUpdate(existingUserProgram, userProgramDTO);

                return existingUserProgram;
            })
            .map(userProgramRepository::save)
            .map(userProgramMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserProgramDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserPrograms");
        return userProgramRepository.findAll(pageable).map(userProgramMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserProgramDTO> findOne(Long id) {
        log.debug("Request to get UserProgram : {}", id);
        return userProgramRepository.findById(id).map(userProgramMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserProgram : {}", id);
        userProgramRepository.deleteById(id);
    }
}
