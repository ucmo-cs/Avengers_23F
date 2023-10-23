package com.avengers.project.controller;
import com.avengers.project.domain.CMuser;
import com.avengers.project.service.CMuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CMuserController {

    private final CMuserService cmUserService;

    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody CMuser user){
        return new ResponseEntity<>(cmUserService.create(user), HttpStatus.CREATED);
    }
}