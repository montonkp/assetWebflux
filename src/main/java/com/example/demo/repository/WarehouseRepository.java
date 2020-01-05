package com.example.demo.repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.example.demo.entity.Warehouse;
public interface WarehouseRepository extends ReactiveCrudRepository<Warehouse, String> {
}