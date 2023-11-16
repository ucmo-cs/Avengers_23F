package com.avengers.project.repository;

import com.avengers.project.domain.CMuser;
import com.avengers.project.domain.ChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Function;

@Repository
public interface ChangeRequestRepository extends JpaRepository<ChangeRequest, Integer> {

}