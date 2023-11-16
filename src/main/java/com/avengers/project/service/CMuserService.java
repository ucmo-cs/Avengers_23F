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

    public CMuser getUser(String userID)
    {
        return cmUserRepository.findByUserID(userID);
    }

    public boolean isGoodLogin(String userID, String password){
        try {
            CMuser cMuser = cmUserRepository.findByUserID(userID);
            if (password.equals(cMuser.getPassword())) {
                System.out.println("User: " + userID + " has been found and password matches");
                return true;
            } else {
                System.out.println("Username or Password are incorrect.");
                return false;
            }
        }
        catch (Exception e){
            System.out.println("Error in finding or evaluating userid and password");
            return false;
        }
    }
}
