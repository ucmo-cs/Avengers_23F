package com.avengers.project.service;

import com.avengers.project.domain.CMuser;
import com.avengers.project.repository.CMuserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CMuserService {

    private final CMuserRepository cmUserRepository;

    public CMuser create(CMuser user){
        return cmUserRepository.save(user);
    }
}
