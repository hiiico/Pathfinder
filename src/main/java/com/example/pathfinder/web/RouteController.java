package com.example.pathfinder.web;

import com.example.pathfinder.service.dtos.RouteShortInfoDto;
import com.example.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String routes(Model model) {
        // single random route
//        RouteShortInfoDto randomRoute = routeService.getRoute();
//        model.addAttribute("route", randomRoute);

        //list of routes
        List<RouteShortInfoDto> routes = routeService.getAll();
        model.addAttribute("allRoutes", routes);

        return "routes";
    }
}
