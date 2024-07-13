package com.apiTPO.technologyHouse.app.controllers;

import com.apiTPO.technologyHouse.app.models.Report;
import com.apiTPO.technologyHouse.app.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Report>> getAll() {
        List<Report> reports = reportService.getAll();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Report> getById(@PathVariable Long id) {
        Report report = reportService.getById(id);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/create")
    public ResponseEntity<Report> create(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("problem") String problem,
            @RequestParam("description") String description,
            @RequestParam("images") MultipartFile[] files) throws IOException {

        Report reportCreated = reportService.create(name, surname, problem, description, files);
        return ResponseEntity.ok(reportCreated);
    }
}
