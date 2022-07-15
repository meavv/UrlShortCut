package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.ShortUrl;

public interface ShortUrlRepository extends CrudRepository<ShortUrl, Integer> {

    ShortUrl findByCode(String code);

}
