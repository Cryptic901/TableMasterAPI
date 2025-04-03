package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.IngredientRepository;
import com.tablemaster_api.abstraction.service.IIngredientService;
import com.tablemaster_api.dto.IngredientDto;
import com.tablemaster_api.entity.Ingredient;
import com.tablemaster_api.mapper.IngredientDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService implements IIngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientDtoMapper ingredientDtoMapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientDtoMapper ingredientDtoMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientDtoMapper = ingredientDtoMapper;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public IngredientDto getIngredientById(long ingredientId) {
        return ingredientDtoMapper.fromEntity(ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found")));
    }
    public String addIngredient(Ingredient ingredient) {
        try {
            ingredientRepository.save(ingredient);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Ingredient created successfully!";
    }

    public String deleteIngredient(long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
        ingredientRepository.delete(ingredient);
        return "Ingredient deleted successfully";
    }

    public IngredientDto updateIngredient(long ingredientId, IngredientDto ingredientDto) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
        ingredientDtoMapper.updateEntityFromDto(ingredientDto, ingredient);
        ingredientRepository.save(ingredient);
        return ingredientDtoMapper.fromEntity(ingredient);
    }
}
