package com.tablemaster_api.controller;

import com.tablemaster_api.dto.IngredientDto;
import com.tablemaster_api.entity.Ingredient;
import com.tablemaster_api.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredients")
@Validated
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<IngredientDto> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ingredientService.addIngredient(ingredient));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable long id) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<IngredientDto> updateIngredient(@PathVariable long id,
             @RequestBody IngredientDto ingredientDto) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredientDto));
    }
}
