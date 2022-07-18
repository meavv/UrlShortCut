package ru.job4j.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.job4j.urlshortcut.model.ShortUrl;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.ServiceUrlShortCut;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class SiteController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SiteController.class.getSimpleName());
    private final ObjectMapper objectMapper;
    private ServiceUrlShortCut services;

    public SiteController(ObjectMapper objectMapper, ServiceUrlShortCut services) {
        this.objectMapper = objectMapper;
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

    @ExceptionHandler(value = { NullPointerException.class })
    public void exceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }

}