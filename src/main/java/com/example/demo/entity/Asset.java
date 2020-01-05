package com.example.demo.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
@Document
@Data
public class Asset {
    @Id
    private String assetID;
    private String assetName;
    private Double pricePerUnit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate importDate;

    private AssetType assetType;
    private Warehouse warehouse;
}