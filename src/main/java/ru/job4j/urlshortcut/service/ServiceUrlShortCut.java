package ru.job4j.urlshortcut.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.ShortUrl;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.ShortUrlRepository;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ServiceUrlShortCut {

    private final SiteRepository siteRepository;
    private final ShortUrlRepository shortUrlRepository;
    private BCryptPasswordEncoder encoder;

    public ServiceUrlShortCut(SiteRepository siteRepository,
                              ShortUrlRepository shortUrlRepository,
                              BCryptPasswordEncoder encoder) {
        this.siteRepository = siteRepository;
        this.shortUrlRepository = shortUrlRepository;
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

    public String convert(ShortUrl shortUrl) {
        var uuid = UUID.randomUUID().toString().split("-")[0];
        shortUrl.setCode(uuid);
        shortUrl.setCount(0);
        shortUrlRepository.save(shortUrl);
        return uuid;
    }

    public String redirect(String code) {
        ShortUrl shortUrl = shortUrlRepository.findByCode(code);
        shortUrlRepository.incrementCount(shortUrl.getId());
        return shortUrl.getUrl();
    }

    public List<ShortUrl> statistic() {
        List<ShortUrl> list = new ArrayList<>();
        shortUrlRepository.findAll().forEach(list::add);
        return list;
    }
}


