package com.example.kt_jpa.controller;

import com.example.kt_jpa.model.dto.VoteDTO;
import com.example.kt_jpa.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping(value = "insert/vote")
    public ResponseEntity<String> insert(@RequestBody VoteDTO voteDTO) {
        return ResponseEntity.ok(voteService.insertVote(voteDTO));
    }
}
