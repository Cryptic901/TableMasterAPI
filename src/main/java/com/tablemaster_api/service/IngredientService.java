package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.IngredientRepository;
import com.tablemaster_api.abstraction.service.IIngredientService;
import com.tablemaster_api.dto.IngredientDto;
import com.tablemaster_api.entity.Ingredient;
import com.tablemaster_api.mapper.IngredientDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Cacheable(value = "ingredients", key = "#ingredientId")
    public IngredientDto getIngredientById(long ingredientId) {
        return ingredientDtoMapper.fromEntity(ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found")));
    }

    @CacheEvict(value = "ingredients", key = "#ingredient.id")
    public IngredientDto addIngredient(Ingredient ingredient) {
            ingredientRepository.save(ingredient);
            return ingredientDtoMapper.fromEntity(ingredient);
    }

    @Caching(evict = {
            @CacheEvict(value = "ingredients", key = "#ingredientId"),
            @CacheEvict(value = "ingredients", allEntries = true)
    })
    @Transactional
    public String deleteIngredient(long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
        ingredientRepository.delete(ingredient);
        return "Ingredient deleted successfully";
    }

    @CacheEvict(value = "ingredients", key = "#ingredientId")
    public IngredientDto updateIngredient(long ingredientId, IngredientDto ingredientDto) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
        ingredientDtoMapper.updateEntityFromDto(ingredientDto, ingredient);
        ingredientRepository.save(ingredient);
        return ingredientDtoMapper.fromEntity(ingredient);
    }
}
