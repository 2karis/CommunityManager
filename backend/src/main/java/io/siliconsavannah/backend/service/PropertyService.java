package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.mapper.PropertyMapper;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.repo.PropertyRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private PropertyRepo propertyRepo;


    public PropertyDto createProperty(PropertyDto property){
        return propertyMapper.entityToDto(propertyRepo.save(propertyMapper.dtoToEntity(property)));
    }

    public List<PropertyDto> readAllPropertys(){
        return propertyRepo.findAll().stream().map(propertyMapper::entityToDto).collect(Collectors.toList());
    }

    public PropertyDto updateProperty(PropertyDto dto) throws Exception {
        Property entity = propertyRepo.findPropertyById(dto.id())
                .orElseThrow(() -> new Exception("property with id "+ dto.id() +" not found"));
        if (dto.address()!= null) entity.setAddress(dto.address());
        if (dto.unit()!= null) entity.setUnit(dto.unit());
        // if (dto.lease()!= null) entity.setLease(dto.lease());
        if (dto.expense()!= null) entity.setExpense(dto.expense());
        return propertyMapper.entityToDto(propertyRepo.save(entity));
    }
    public void deleteProperty(int id){
        propertyRepo.deletePropertyById(id);
    }

    public PropertyDto findPropertyById(int id) throws Exception {
        return propertyMapper.entityToDto(propertyRepo.findPropertyById(id)
                .orElseThrow(() -> new Exception("property with id "+ id +" not found")));
    }
}