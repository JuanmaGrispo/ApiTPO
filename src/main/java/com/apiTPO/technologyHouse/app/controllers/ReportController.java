package com.apiTPO.technologyHouse.app.controllers;

import com.apiTPO.technologyHouse.app.dtos.ReportDTO;
import com.apiTPO.technologyHouse.app.models.Report;
import com.apiTPO.technologyHouse.app.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<Report>> getAll() {
        List<Report> products = reportService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getById(@PathVariable Long id) {
        Report report = reportService.getById(id);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/create")
    public ResponseEntity<Report> create(@RequestBody ReportDTO reportDTO) {
        Report reportCreated = reportService.create(reportDTO);
        return ResponseEntity.ok(reportCreated);
    }
}
