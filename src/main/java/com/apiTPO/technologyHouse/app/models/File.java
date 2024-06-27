package com.apiTPO.technologyHouse.app.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Lob
    @Column(columnDefinition="LONGBLOB")
    private byte[] data;

    private String extension;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;
}
