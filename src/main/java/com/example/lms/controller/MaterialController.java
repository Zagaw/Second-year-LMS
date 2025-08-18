package com.example.lms.controller;

import com.example.lms.entity.Material;
import com.example.lms.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("course/{courseId}")
    public List<Material> getMaterialsByCourse(@PathVariable Long courseId) {
        return materialService.getMaterialByCourse(courseId);
    }

    @PostMapping("/course/{courseId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<Material> addMaterial(@PathVariable Long courseId, @RequestBody Material material) {
        return ResponseEntity.ok(materialService.createMaterial(courseId, material));
    }

    @DeleteMapping("/course/{courseId}/{materialId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<String> deleteMaterial(@PathVariable Long courseId, @PathVariable Long materialId) {
        materialService.deleteMaterial(courseId, materialId);
        return ResponseEntity.ok("Material deleted successfully");
    }
}

