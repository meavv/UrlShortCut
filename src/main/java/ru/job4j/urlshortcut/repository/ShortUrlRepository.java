package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.ShortUrl;

public interface ShortUrlRepository extends CrudRepository<ShortUrl, Integer> {

    ShortUrl findByCode(String code);

    @Transactional
    @Modifying
    @Query("update ShortUrl s set s.count = s.count + 1 where s.id = :id")
    void incrementCount(@Param("id") int id);

}
