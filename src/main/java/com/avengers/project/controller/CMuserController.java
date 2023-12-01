package com.avengers.project.controller;
import com.avengers.project.domain.CMuser;
import com.avengers.project.service.CMuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CMuserController {

    private final CMuserService cmUserService;
    @CrossOrigin
    @PostMapping("/addUser")
    public ResponseEntity<?> save(@RequestBody CMuser user)
    {
        if(user.getUserID()!=null&&user.getPassword()!=null) {
            return new ResponseEntity<>(cmUserService.create(user), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin
    @PostMapping("/checkUserLogin")
    public ResponseEntity<?> checkUserLogin(@RequestBody CMuser user ) {
        boolean isGoodLogin = cmUserService.isGoodLogin(user.getUserID(), user.getPassword());

        if (isGoodLogin) {
            CMuser goodUser = cmUserService.getUser(user.getUserID());
            return new ResponseEntity(goodUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}