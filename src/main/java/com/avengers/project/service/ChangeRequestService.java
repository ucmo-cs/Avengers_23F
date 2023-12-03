package com.avengers.project.service;

import com.avengers.project.domain.CMuser;
import com.avengers.project.domain.ChangeRequest;
import com.avengers.project.repository.CMuserRepository;
import com.avengers.project.repository.ChangeRequestDao;
import com.avengers.project.repository.ChangeRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChangeRequestService {

    private final ChangeRequestDao changeRequestDao;
    private final CMuserRepository cmUserRepository;
    private final ChangeRequestRepository changeRequestRepository;

    public ChangeRequest create(ChangeRequest changeRequest, String userID){

        CMuser cmUser = cmUserRepository.findByUserID(userID);
        changeRequest.setUser(cmUser);

        return changeRequestRepository.save(changeRequest);
    }


    public List<ChangeRequest> getViewableRequest(String userID){
        CMuser cMuser = cmUserRepository.findByUserID(userID);
        List<ChangeRequest> list =  changeRequestDao.getViewableRequest(cMuser);
        for(int i = 0; i< list.size();i++) {
            Optional<ChangeRequest> changeRequest = (changeRequestRepository.findById(list.get(i).getChangeNumber()));
            list.get(i).setUser(changeRequest.get().getUser());
        }
        return list;
    }

    public List<ChangeRequest> getCompletedRequest(){
        List<ChangeRequest> list =  changeRequestDao.getCompletedRequest();
        for(int i = 0; i< list.size();i++) {
            Optional<ChangeRequest> changeRequest = (changeRequestRepository.findById(list.get(i).getChangeNumber()));
            list.get(i).setUser(changeRequest.get().getUser());
        }
        return list;
    }

    public Optional<ChangeRequest> viewOneChangeRequest(int changeNumber){

        return changeRequestRepository.findById(changeNumber);
    }

    public void updateStatusWithStatus(int changeNumber, String status){
        changeRequestDao.updateStatusWithStatus(changeNumber, status);
    }

    public void updateStatusApproved(int changeNumber){
        Optional<ChangeRequest> changeRequest = changeRequestRepository.findById(changeNumber);
        String status = getHigherStatus(changeRequest.get().getStatus());
        changeRequestDao.updateStatusWithStatus(changeNumber, status);
    }
    public void updateStatusDenied(int changeNumber){
        String status = "denied";
        changeRequestDao.updateStatusWithStatus(changeNumber, status);
    }

    public String getHigherStatus(String currentStatus){
        String status;
        switch(currentStatus) {
            case "open":
                status = "frozen";
                break;
            case "frozen":
                status = "department";
                break;
            case "department":
                status = "application";
                break;
            case "application":
                status = "completed";
                break;
            default:
                status = null;
        }
        return status;
    }

    public void updateImplStart(int cNumber, String startTime){
        changeRequestDao.updateSingleChangeRequest(cNumber, startTime, "actual_impl_start");
    }
    public void updateImplementor(int cNumber, String implementorName){
        changeRequestDao.updateSingleChangeRequest(cNumber, implementorName, "implementor");
    }
    public void updateImplementation(int cNumber, String implementationStatus){
        changeRequestDao.updateSingleChangeRequest(cNumber, implementationStatus, "implementation");
    }
    public void cleanRequest(){
        changeRequestDao.cleanRequest();
    }
}

