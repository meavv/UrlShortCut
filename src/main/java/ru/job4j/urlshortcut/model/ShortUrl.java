package ru.job4j.urlshortcut.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "text")
    private String url;
    private String code;

    private int count = new AtomicInteger().get();

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShortUrl shortUrl = (ShortUrl) o;
        return id == shortUrl.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
