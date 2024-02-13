package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.mapper.PropertyMapper;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.repo.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private PropertyRepo propertyRepo;

    public Property createProperty(Property property){
        return propertyRepo.save(property);
    }

    public List<PropertyDto> readAllPropertys(){
        return propertyRepo.findAll().stream().map(propertyMapper).collect(Collectors.toList());
    }

    public PropertyDto updateProperty(PropertyDto propertyDto){
        Optional<Property> property = propertyRepo.findById(propertyDto.id());
        property.ifPresent(
                el ->{
                    if (propertyDto.address()!= null) el.setAddress(propertyDto.address());
                    if (propertyDto.unit()!= null) el.setUnit(propertyDto.unit());
                    if (propertyDto.lease()!= null) el.setLease(propertyDto.lease());
                    if (propertyDto.expense()!= null) el.setExpense(propertyDto.expense());
                    propertyRepo.save(el);
                }
        );
        return propertyDto;
    }
    public void deleteProperty(int id){
        propertyRepo.deleteById(id);
    }

    public PropertyDto findPropertyById(int id){
        return propertyRepo.findById(id).stream().map(propertyMapper).findAny().orElseThrow(RuntimeException::new);
    }
}