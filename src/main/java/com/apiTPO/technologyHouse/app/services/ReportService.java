package com.apiTPO.technologyHouse.app.services;

import com.apiTPO.technologyHouse.app.dtos.ImageDTO;
import com.apiTPO.technologyHouse.app.dtos.ReportDTO;
import com.apiTPO.technologyHouse.app.models.File;
import com.apiTPO.technologyHouse.app.models.Report;
import com.apiTPO.technologyHouse.app.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
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
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Report create(ReportDTO reportDTO) {
        Report report = new Report();
        report.setName(reportDTO.getName());
        report.setSurname(reportDTO.getSurname());
        report.setProblem(reportDTO.getProblem());
        report.setDescription(reportDTO.getDescription());

        List<File> files = new ArrayList<>();
        for (ImageDTO imageDTO : reportDTO.getImages()) {
            File fileEntity = new File();

            byte[] decodedBytes = Base64.getDecoder().decode(imageDTO.getData());
            fileEntity.setName(imageDTO.getName());
            fileEntity.setData(decodedBytes);
            fileEntity.setExtension(imageDTO.getExtension());
            fileEntity.setReport(report);
            files.add(fileEntity);
        }

        report.setImages(files);
        return reportRepository.save(report);
    }
}

