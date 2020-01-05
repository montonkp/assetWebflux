package com.example.demo;
import reactor.core.publisher.Mono;
import com.example.demo.entity.Asset;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TestMock {
    public static List<Asset> mockAssets() {
        List<Asset> assets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            assets.add(mockAsset());
        }
        return assets;
    }

    public static Asset mockAsset() {
        Asset a = new Asset();
        a.setAssetName("HP pavilion");
        a.setPricePerUnit((double)40000.00);
        a.setImportDate(LocalDate.now());
        return a;
    }

    public static Mono<Asset> mockAssetForUpdate() {
        Asset a = mockAsset();
        a.setAssetName("macbook");
        return Mono.just(a);
    }

    public static Mono<Asset> mockAssetMono() {
        return Mono.just(mockAsset());
    }
}