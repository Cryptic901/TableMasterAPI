package com.tablemaster_api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablemaster_api.abstraction.repository.DishRepository;
import com.tablemaster_api.abstraction.service.IDishService;
import com.tablemaster_api.dto.DishDto;
import com.tablemaster_api.entity.Dish;
import com.tablemaster_api.entity.Ingredient;
import com.tablemaster_api.mapper.DishDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DishService implements IDishService {

    private final DishRepository dishRepository;
    private final ObjectMapper objectMapper;

    public DishService(DishRepository dishRepository, @Qualifier("objectMapper") ObjectMapper objectMapper) {
        this.dishRepository = dishRepository;
        this.objectMapper = objectMapper;
    }

    public List<DishDto> getAllDishes() {
        return dishRepository.findAll()
                .stream().map(DishDtoMapper::fromEntity).toList();
    }

    public DishDto getDishById(long id) {
        return DishDtoMapper.fromEntity(dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found")));
    }

    public String addDish(Dish dish) {
        if (dish == null) {
            throw new IllegalArgumentException("Dish is null or have bad credentials");
        }
        dishRepository.save(dish);
        return "Dish created successfully!";
    }

    public String deleteDish(long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        return "Dish deleted successfully";
    }

    public DishDto updateDish(long dishId, Map<String, Object> params) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        if (params.containsKey("name")) {
            dish.setName((String) params.get("name"));
        }
        if (params.containsKey("price")) {
            dish.setPrice((BigDecimal) params.get("price"));
        }
        if (params.containsKey("ingredients")) {
            List<Ingredient> ingredients = objectMapper.convertValue(
                    params.get("ingredients"),
                    new TypeReference<>() {}
            );
            dish.setIngredients(ingredients);
        }
        dishRepository.save(dish);
        return DishDtoMapper.fromEntity(dish);
    }
}
