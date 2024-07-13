package com.apiTPO.technologyHouse.app.services;

import com.apiTPO.technologyHouse.app.models.File;
import com.apiTPO.technologyHouse.app.models.Report;
import com.apiTPO.technologyHouse.app.models.User;
import com.apiTPO.technologyHouse.app.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    public Report getById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
    }

    public Report create(String name, String surname, String problem, String description, MultipartFile[] files) throws IOException {
        Report report = new Report();
        report.setName(name);
        report.setSurname(surname);
        report.setProblem(problem);
        report.setDescription(description);

        List<File> fileEntities = new ArrayList<>();
        for (MultipartFile file : files) {
            File fileEntity = new File();
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setExtension(file.getContentType());
            fileEntity.setData(file.getBytes());
            fileEntity.setReport(report);
            fileEntities.add(fileEntity);
        }

        report.setImages(fileEntities);
        return reportRepository.save(report);
    }
}
