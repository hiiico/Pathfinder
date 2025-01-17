package com.example.pathfinder.web;

import com.example.pathfinder.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.dsig.XMLObject;

@Controller
public class GeocodingController {

    private final GeocodingService geocodingService;

    @Autowired
    public GeocodingController(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @PostMapping("/search")
    public String search(@RequestParam("query") String query, Model model){
        XMLObject location;
        try {
            location = geocodingService.search(query);
            model.addAttribute("location", location);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "index";
    }
}