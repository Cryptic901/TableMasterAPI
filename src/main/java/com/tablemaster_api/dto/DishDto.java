package com.tablemaster_api.dto;

import com.tablemaster_api.entity.Ingredient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public record DishDto(
        String name,
        BigDecimal price,
        List<Ingredient> ingredients) implements Serializable {}
