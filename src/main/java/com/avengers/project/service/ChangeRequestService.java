package com.avengers.project.service;

import com.avengers.project.domain.CMuser;
import com.avengers.project.domain.ChangeRequest;
import com.avengers.project.repository.CMuserRepository;
import com.avengers.project.repository.ChangeRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangeRequestService {


    private final ChangeRequestRepository changeRequestRepository;
    private final CMuserRepository cmUserRepository;

    public ChangeRequest create(ChangeRequest changeRequest, String userID){

        CMuser cmUser = cmUserRepository.findByUserId(userID);
        changeRequest.setUser(cmUser);

        return changeRequestRepository.save(changeRequest);
    }
}

