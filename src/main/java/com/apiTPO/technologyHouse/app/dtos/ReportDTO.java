package com.apiTPO.technologyHouse.app.dtos;

import com.apiTPO.technologyHouse.app.models.File;
import lombok.Data;

import java.util.List;

@Data
public class ReportDTO {
    private String name;
    private String surname;
    private String problem;
    private String description;
    private List<ImageDTO> images;
}
