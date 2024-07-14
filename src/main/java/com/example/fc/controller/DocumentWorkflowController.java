package com.example.fc.controller;

import com.example.fc.dto.Approval;
import com.example.fc.dto.Document;
import com.example.fc.service.DocumentWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentWorkflowController {

    @Autowired
    private DocumentWorkflowService documentWorkflowService;

    @PostMapping("/start-process")
    public ResponseEntity<Void> startProcess(Document document) {
        documentWorkflowService.startProcess(document);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/tasks")
    public List<Document> getTasks(@RequestParam String assignee) {
        return documentWorkflowService.getTasks(assignee);
    }

    @PostMapping("/review-document")
    public ResponseEntity<Void> reviewDocument(@RequestBody Approval approval) {
        documentWorkflowService.reviewDocument(approval);
        return ResponseEntity.created(null).build();
    }

}
