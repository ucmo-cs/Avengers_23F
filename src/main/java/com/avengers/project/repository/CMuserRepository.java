package com.avengers.project.repository;

import com.avengers.project.domain.CMuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CMuserRepository extends JpaRepository<CMuser, Integer> {

    CMuser findByUserId(String userID);

}
