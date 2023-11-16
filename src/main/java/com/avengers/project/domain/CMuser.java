package com.avengers.project.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class CMuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userID;
    private String password;
    private String fName;
    private String lName;
    private String securitylvl;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ChangeRequest> ChangeRequests = new ArrayList<>();

}