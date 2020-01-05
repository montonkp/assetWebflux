package com.example.demo.repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.example.demo.entity.Asset;
public interface AssetRepository extends ReactiveCrudRepository<Asset, String> {
}