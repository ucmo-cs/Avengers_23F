package com.avengers.project.repository;

import com.avengers.project.domain.CMuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public interface CMuserRepository extends JpaRepository<CMuser, Integer> {



    CMuser findByUserID(String userID);
    Integer findIdByUserID(String userID);

    String findSecuritylvlByUserID(String userID);
    CMuser findUserIDById(Integer id);

    CMuser findByUserIDAndPassword(String userID, String password);


}
