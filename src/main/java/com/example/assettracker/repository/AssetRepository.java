package com.example.assettracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.assettracker.model.Asset;

public interface AssetRepository extends MongoRepository<Asset, String> {
    // Custom query methods can be defined here if needed

}
