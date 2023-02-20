package com.example.fc.service;

import com.example.fc.dto.Approval;
import com.example.fc.dto.Document;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DocumentWorkflowService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void startProcess(Document document) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("id", document.getId());
        variables.put("user", document.getUser());
        variables.put("content", document.getContent());
        runtimeService.startProcessInstanceByKey("documentWorkflow", variables);
    }

    @Transactional
    public List<Document> getTasks(String assignee) {
        logger.info("Number of tasks : {}", taskService.createTaskQuery().list());
        // List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        List<Task> tasks = taskService.createTaskQuery().list();
        return tasks.stream().map(
                task -> {
                    Map<String, Object> variables = taskService.getVariables(task.getId());
                    return new Document(task.getId(), (String) variables.get("user"),
                            (String) variables.get("content"));
                }).collect(Collectors.toList());
    }

    @Transactional
    public void reviewDocument(Approval approval) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("isDocumentApproved", approval.getStatus());
        taskService.complete(approval.getId(), variables);
    }
}
