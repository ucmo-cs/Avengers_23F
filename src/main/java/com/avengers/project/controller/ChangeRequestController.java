package com.avengers.project.controller;

import com.avengers.project.domain.ChangeRequest;
import com.avengers.project.service.ChangeRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChangeRequestController {

    private final ChangeRequestService changeRequestService;

    @PostMapping("/changeRequest/{userID}")
    public ResponseEntity<?> change(@RequestBody ChangeRequest changeRequest, @PathVariable String userID) {

        return new ResponseEntity<>(changeRequestService.create(changeRequest, userID), HttpStatus.CREATED);
    }
}
