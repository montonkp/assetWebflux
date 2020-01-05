package com.example.demo.repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.example.demo.entity.AssetType;
public interface AssetTypeRepository extends ReactiveCrudRepository<AssetType, String> {
}