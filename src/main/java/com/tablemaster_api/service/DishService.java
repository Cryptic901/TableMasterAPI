package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.DishRepository;
import com.tablemaster_api.abstraction.service.IDishService;
import com.tablemaster_api.dto.DishDto;
import com.tablemaster_api.mapper.DishDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService implements IDishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<DishDto> getAllDishes() {
        return dishRepository.findAll()
                .stream().map(DishDtoMapper::fromEntity).toList();
    }

    public DishDto getDishById(Long id) {
        return DishDtoMapper.fromEntity(dishRepository.findById(id));
    }
}
