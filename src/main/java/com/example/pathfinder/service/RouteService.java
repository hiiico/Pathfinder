package com.example.pathfinder.service;

import com.example.pathfinder.data.RouteRepository;
import com.example.pathfinder.service.dtos.RouteShortInfoDto;
import com.example.pathfinder.model.entities.Picture;
import com.example.pathfinder.model.entities.Route;
import com.example.pathfinder.web.dto.AddRouteDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
//    private final Random random;
    private final ModelMapper modelMapper;
    private final CurrentUserService currentUserService;
    private final FileService fileService;

    // List of routes
    @Transactional
    public List<RouteShortInfoDto> getAll() {
        return routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfo)
                .toList();
    }

    // single random route
/*    public RouteShortInfoDto getRoute() {
        long routeCount = routeRepository.count();
        long randomId = random.nextLong(routeCount) + 1;

        Optional<Route> route = routeRepository.findById(randomId);

        if(route.isEmpty()) {
            //throw exception;
        }

        RouteShortInfoDto dto = modelMapper.map(route.get(), RouteShortInfoDto.class);
        Optional<Picture> first = route.get().getPictures().stream().findFirst();
        dto.setImageUrl(first.get().getUrl());

        return dto;
    }
*/
    protected RouteShortInfoDto mapToShortInfo(Route route) {
        RouteShortInfoDto dto = modelMapper.map(route, RouteShortInfoDto.class);
        Optional<Picture> first = route.getPictures().stream().findFirst();
        dto.setImageUrl(first.get().getUrl());

        return dto;
    }

    public void add(AddRouteDto data, MultipartFile gpxCoordinates) throws IOException {
        // TODO check if route exist
        if(!routeExist(data)) {
            createRoute(data, gpxCoordinates);
        }

    }

    private boolean routeExist(AddRouteDto data) {
        return routeRepository.findByName(data.getName()) != null;
    }

    private void createRoute(AddRouteDto data, MultipartFile gpxCoordinates) throws IOException {
        if(fileService.saveUserFile(gpxCoordinates)) {
            Route toInsert = modelMapper.map(data, Route.class);
            toInsert.setAuthor(currentUserService.getUser());
            toInsert.setLevel(data.getLevel());

            String route = String.valueOf(Files.readAllLines(Path.of(
                    "src" + File.separator
                            + "main" + File.separator
                            + "resources" + File.separator
                            + "uploads"+ File.separator
                            + currentUserService.getUser().getUsername() + File.separator
                            + gpxCoordinates.getOriginalFilename()
            ))).replace("[", "")
                    .replace("]", "");

            toInsert.setGpxCoordinates(route);
            routeRepository.save(toInsert);
        }
    }

}
