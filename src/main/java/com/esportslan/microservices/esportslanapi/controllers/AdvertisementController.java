package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.models.Advertisement;
import com.esportslan.microservices.esportslanapi.models.Feedback;
import com.esportslan.microservices.esportslanapi.services.AdvertisementService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Advertisement", description = "Advertisement APIs")
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @Operation(
            summary = "Fetch advertisements details",
            description = "Fetch advertisements details"
    )
    @GetMapping("/fetch-advertisements")
    @Retry(name = "fetch-advertisements-retry")
    public ResponseEntity<List<Advertisement>> fetchAdvertisementDetails() {
        List<Advertisement> advertisements = advertisementService.fetchALlAdvertisements();
        return ResponseEntity.status(HttpStatus.OK).body(advertisements);
    }

    @Operation(
            summary = "Save advertisement details",
            description = "Save advertisement details"
    )
    @PostMapping("/save-advertisement")
    @Retry(name = "save-advertisement-retry")
    public ResponseEntity<Void> saveAdvertisementDetails(@RequestBody Advertisement advertisement) {
        advertisementService.saveAdvertisement(advertisement);
        return ResponseEntity.noContent().build();
    }
}
