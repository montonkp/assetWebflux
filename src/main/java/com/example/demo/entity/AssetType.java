package com.example.demo.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
@Data
public class AssetType {
    @Id
    private String assetTypeID;
    private String assetTypeName;
}