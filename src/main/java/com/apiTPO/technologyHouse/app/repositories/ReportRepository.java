package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
