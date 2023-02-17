package com.example.qlbh.controller;

import com.example.qlbh.model.dto.FeedbackDTO;
import com.example.qlbh.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = "insert")
    public ResponseEntity<String> insertFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return ResponseEntity.ok(feedbackService.insertFeedback(feedbackDTO));
    }
}
