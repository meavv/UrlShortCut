package ru.job4j.urlshortcut.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceUrlShortCut {

    private final SiteRepository siteRepository;
    private BCryptPasswordEncoder encoder;

    public ServiceUrlShortCut(SiteRepository siteRepository, BCryptPasswordEncoder encoder) {
        this.siteRepository = siteRepository;
        this.encoder = encoder;
    }

    public Site reg(Site site) {
        var uuid = UUID.randomUUID().toString().split("-");
        site.setLogin(uuid[2]);
        var pass = uuid[0];
        site.setPassword(pass);
        saveEncoder(site);
        site.setPassword(uuid[0]);
        return site;
    }

    private void saveEncoder(Site site) {
        site.setPassword(encoder.encode(site.getPassword()));
        siteRepository.save(site);
    }

    public List<Site> findAllSites() {
        List<Site> list = new ArrayList<>();
        siteRepository.findAll().forEach(list::add);
        return list;
    }

    public Site findByLogin(String login) {
        return siteRepository.findByLogin(login);
    }
}


