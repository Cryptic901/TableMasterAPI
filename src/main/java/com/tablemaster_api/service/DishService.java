package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.DishRepository;
import com.tablemaster_api.abstraction.service.IDishService;
import com.tablemaster_api.dto.DishDto;
import com.tablemaster_api.entity.Dish;
import com.tablemaster_api.mapper.DishDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishService implements IDishService {

    private final DishRepository dishRepository;
    private final DishDtoMapper dishDtoMapper;

    public DishService(DishRepository dishRepository, DishDtoMapper dishDtoMapper) {
        this.dishRepository = dishRepository;
        this.dishDtoMapper = dishDtoMapper;
    }

    public List<DishDto> getAllDishes() {
        return dishRepository.findAll()
                .stream().map(dishDtoMapper::fromEntity).toList();
    }

    @Cacheable(value = "dishes", key = "#id")
    public DishDto getDishById(long id) {
        return dishDtoMapper.fromEntity(dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found")));
    }

    @CacheEvict(value = "dishes", key = "#dish.name")
    public Dish addDish(DishDto dish) {
        dishRepository.save(dishDtoMapper.toEntity(dish));
        return dishDtoMapper.toEntity(dish);
    }

    @Caching(evict = {
            @CacheEvict(value = "dishes", key = "#dishId"),
            @CacheEvict(value = "dishes", allEntries = true)
    })
    @Transactional
    public String deleteDish(long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        dishRepository.delete(dish);
        return "Dish deleted successfully";
    }

    @CacheEvict(value = "dishes", key = "#dishId")
    public DishDto updateDish(long dishId, DishDto dishDto) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        dishDtoMapper.updateEntityFromDto(dishDto, dish);
        dishRepository.save(dish);
        return dishDtoMapper.fromEntity(dish);
    }
}
