package com.example.pathfinder.web;

import com.example.pathfinder.model.enums.CategoryType;
import com.example.pathfinder.model.enums.UserLevel;
import com.example.pathfinder.service.dtos.RouteShortInfoDto;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.web.dto.AddRouteDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
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

    @GetMapping("add-route")
    public ModelAndView addRoute() {
        ModelAndView mnv = new ModelAndView("add-route");
        mnv.addObject("route", new RouteShortInfoDto());
        mnv.addObject("levels", UserLevel.values());
        mnv.addObject("categoryTypes", CategoryType.values());
        return mnv;
    }

    @ModelAttribute("routeData")
    public AddRouteDto routeData() {
        return  new AddRouteDto();
    }

    @PostMapping("add-route")
    public String doAddRoute(
        @Valid AddRouteDto data,
        @RequestParam("gpxCoordinates") MultipartFile gpxCoordinates,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) throws IOException {
         if(bindingResult.hasErrors()) {
             redirectAttributes.addFlashAttribute("add-route", data);
             redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.AddRouteDto", bindingResult);
             return "redirect:/add-route";
         }
         routeService.add(data, gpxCoordinates);
         return "redirect:/route-details";
    }

    @GetMapping("route-details")
    public ModelAndView routeDetails() {
        return new ModelAndView("route-details");
    }

}
