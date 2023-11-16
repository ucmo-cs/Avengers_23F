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
public class CMuserController {

    private final CMuserService cmUserService;

    @PostMapping("/addUser")
    public ResponseEntity<?> save(@RequestBody CMuser user)
    {
        return new ResponseEntity<>(cmUserService.create(user), HttpStatus.CREATED);
    }

    @PostMapping("/checkUserLogin")
    public ResponseEntity<?> checkUserLogin(@RequestBody CMuser user ) {
        boolean isGoodLogin = cmUserService.isGoodLogin(user.getUserID(), user.getPassword());

        if (isGoodLogin) {
            CMuser goodUser = cmUserService.getUser(user.getUserID());
            return new ResponseEntity(goodUser,HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}