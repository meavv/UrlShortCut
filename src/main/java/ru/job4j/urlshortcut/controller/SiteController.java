package ru.job4j.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.job4j.urlshortcut.model.ShortUrl;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.ServiceUrlShortCut;

import java.net.http.HttpClient;
import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<List<Site>> getAll() {
        return new ResponseEntity<>(
                services.findAllSites(),
                HttpStatus.OK
        );
    }

    @PostMapping("/convert")
    public ResponseEntity<String> convert(@RequestBody ShortUrl s) {
        return new ResponseEntity<>(
                services.convert(s),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/redirect/{code}")
    public RedirectView redirect(@PathVariable String code) {
        return new RedirectView(services.redirect(code), true);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<ShortUrl>> stat() {
        return new ResponseEntity<>(
                services.statistic(),
                HttpStatus.OK
        );
    }

}