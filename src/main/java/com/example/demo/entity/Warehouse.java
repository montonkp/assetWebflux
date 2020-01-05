package com.example.demo.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
@Data
public class Warehouse {
    @Id
    private String warehouseID;
    private String warehouseName;
    private String address;
}