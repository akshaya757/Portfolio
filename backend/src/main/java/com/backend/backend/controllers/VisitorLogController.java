package com.backend.backend.controllers;

import com.backend.backend.model.VisitorLog;
import com.backend.backend.repository.VisitorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class VisitorLogController {

    @Autowired
    private VisitorLogRepository repo;

    @GetMapping("/logs")
    public List<VisitorLog> getLogs() {
        return repo.findAll();
    }

    @GetMapping("/logs/summary")
    public Map<String, Object> getVisitorSummary() {
        List<VisitorLog> logs = repo.findAll();

        Map<String, Long> deviceTypeCount = logs.stream()
                .collect(Collectors.groupingBy(VisitorLog::getDeviceType, Collectors.counting()));

        Map<String, Object> result = new HashMap<>();
        result.put("totalVisits", logs.size());
        result.put("deviceTypes", deviceTypeCount);
        return result;
    }

}


