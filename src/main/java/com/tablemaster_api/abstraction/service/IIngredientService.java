package com.tablemaster_api.abstraction.service;

import com.tablemaster_api.dto.IngredientDto;
import com.tablemaster_api.entity.Ingredient;

import java.util.List;
import java.util.Map;

public interface IIngredientService {

    List<Ingredient> getAllIngredients();

    IngredientDto getIngredientById(long ingredientId);

    String addIngredient(Ingredient ingredient);

    String deleteIngredient(long ingredientId);

    IngredientDto updateIngredient(long ingredientId, IngredientDto ingredientDto);
}
