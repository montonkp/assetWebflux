package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
@RestController
@RequestMapping("/assets")
public class AssetController {
    
    private AssetRepository assetRepository;

    @Autowired
    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping
    public Flux<Asset> getAssets() {
        return assetRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Asset>
    getAsset(@PathVariable String id) {
        return assetRepository.findById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<Asset>> saveAsset(@RequestBody Mono<Asset> assetMono) {
        return assetMono.flatMap(asset -> assetRepository.save(asset).map(saved ->
                ResponseEntity.created(URI.create(String.format("/assets/%s", saved.getAssetID()))).body(saved)));
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Asset>> updateAsset(@RequestBody Mono<Asset> assetMono,@PathVariable String id) {
        
        return assetMono.flatMap(a -> assetRepository.findById(id).flatMap(s -> {
            a.setAssetID(s.getAssetID());
            return assetRepository.save(a);
        })).map(ResponseEntity::ok).
        defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAsset(@PathVariable String id) {
        return assetRepository.deleteById(id);
    }
}