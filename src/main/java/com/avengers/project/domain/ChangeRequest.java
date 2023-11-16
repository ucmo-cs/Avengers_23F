package com.avengers.project.domain;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="changerequest")
public class ChangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer changeNumber;
    //private String changeNumber;
    private String applicationID;
    private String otherDepartment1;
    private String otherDepartment2;
    private String reasonNumber;
    private String reasonType;
    private String changeType;
    private String changeDesc;
    private String changeWhy;
    private String changeWhat;
    private String changeWindowStart;
    private String changeWindowStop;
    private String actualImplStart;
    private String backoutPlan;
    private String backoutMinutes;
    private Integer risk;
    private String implementor;
    private String implementation;
    private String status;


    @ManyToOne
    @JoinColumn(name = "id")
    private CMuser user;

}


/*
changeRequest input values
changeNumber(9), userID(9), applicationID(3),
otherdepartment1(3), otherdepartment2(3), reasonNumber(25), reasonType(25),
changeType(25), changeDesc(80), changeWhy(25), changeWhat(25),
changeWindowStart(25), changeWindowStop(25), actualImplStart(25), backoutPlan(25),
 backoutMinutes(25), risk(int), implementor(25), implementation(25), status(25)
 */