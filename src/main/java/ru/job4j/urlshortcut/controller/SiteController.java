package ru.job4j.urlshortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.ServiceUrlShortCut;

@RestController
public class SiteController {


    private ServiceUrlShortCut services;

    public SiteController(ServiceUrlShortCut services) {
        this.services = services;
    }

    @PostMapping("/registration")
    public ResponseEntity<Site> reg(@RequestBody Site site) {
        Site rsl = services.reg(site);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }

}