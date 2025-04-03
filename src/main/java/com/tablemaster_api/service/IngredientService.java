package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.IngredientRepository;
import com.tablemaster_api.abstraction.service.IIngredientService;
import com.tablemaster_api.dto.IngredientDto;
import com.tablemaster_api.entity.Ingredient;
import com.tablemaster_api.mapper.IngredientDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IngredientService implements IIngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public IngredientDto getIngredientById(long ingredientId) {
        return IngredientDtoMapper.fromEntity(ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found")));
    }
    public String addIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient is null or have bad credentials");
        }
        ingredientRepository.save(ingredient);
        return "Ingredient created successfully!";
    }

    public String deleteIngredient(long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
        return "Ingredient deleted successfully";
    }

    public IngredientDto updateIngredient(long ingredientId, Map<String, Object> params) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
        if (params.containsKey("name")) {
            ingredient.setName((String) params.get("name"));
        }
        ingredientRepository.save(ingredient);
        return IngredientDtoMapper.fromEntity(ingredient);
    }
}
