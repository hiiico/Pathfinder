package com.example.pathfinder.service;

import com.example.pathfinder.data.RouteRepository;
import com.example.pathfinder.service.dtos.RouteShortInfoDto;
import com.example.pathfinder.model.entities.Picture;
import com.example.pathfinder.model.entities.Route;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
//    private final Random random;
    private final ModelMapper modelMapper;

    public RouteService(RouteRepository routeRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
//        this.random = new Random();
    }

    // List of routes
    @Transactional
    public List<RouteShortInfoDto> getAll() {
        return routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfo)
                .toList();
    }

    // single random route
//    public RouteShortInfoDto getRoute() {
//        long routeCount = routeRepository.count();
//        long randomId = random.nextLong(routeCount) + 1;
//
//        Optional<Route> route = routeRepository.findById(randomId);
//
//        if(route.isEmpty()) {
//            //throw exception;
//        }
//
//        RouteShortInfoDto dto = modelMapper.map(route.get(), RouteShortInfoDto.class);
//        Optional<Picture> first = route.get().getPictures().stream().findFirst();
//        dto.setImageUrl(first.get().getUrl());
//
//        return dto;
//    }

    protected RouteShortInfoDto mapToShortInfo(Route route) {
        RouteShortInfoDto dto = modelMapper.map(route, RouteShortInfoDto.class);
        Optional<Picture> first = route.getPictures().stream().findFirst();
        dto.setImageUrl(first.get().getUrl());

        return dto;
    }

}
