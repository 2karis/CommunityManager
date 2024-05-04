package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.dto.PropertyDto;
import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.mapper.PropertyMapper;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.model.Property;
import io.siliconsavannah.backend.repo.PropertyRepo;
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
public class PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private PropertyRepo propertyRepo;

    public Property createProperty(Property property){
        return propertyRepo.save(property);
    }


    public Mono<PropertyDto> createProperty(Mono<PropertyDto> propertyDto){
        return propertyDto
                .map(propertyMapper::dtoToEntity)
                .map(propertyRepo::save)
                .map(propertyMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<PropertyDto> readAllPropertys(){
        return Flux.fromStream(() -> propertyRepo.findAll().stream())
                .map(propertyMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<PropertyDto> updateProperty(Mono<PropertyDto> propertyDto){

        return propertyDto
                .flatMap(dto-> Mono.fromSupplier(()->propertyRepo.findById(dto.id()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(entity->{
                            if (dto.address()!= null) entity.setAddress(dto.address());
                            if (dto.unit()!= null) entity.setUnit(dto.unit());
                            if (dto.lease()!= null) entity.setLease(dto.lease());
                            if (dto.expense()!= null) entity.setExpense(dto.expense());
                            return propertyRepo.save(entity);
                        })
                )
                .filter(Objects::nonNull)
                .map(propertyMapper::entityToDto)
                .switchIfEmpty(Mono.error(Throwable::new))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteProperty(int id){
        return Mono.fromRunnable(()->{propertyRepo.deleteById(id);})
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<PropertyDto> findPropertyById(int id){
        return Mono.fromSupplier(()->propertyRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(propertyMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}