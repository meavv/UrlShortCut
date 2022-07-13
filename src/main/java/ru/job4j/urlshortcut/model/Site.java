package ru.job4j.urlshortcut.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String site;

    @NotBlank
    private String login;

    @NotNull
    private String password;

    public String getSite() {
        return site;
    }

    public void setSite(String siteUrl) {
        this.site = siteUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Site{" + "id=" + id
                + ", site='" + site + '\''
                + ", login='" + login + '\''
                + ", password='" + password
                + '\'' + '}';
    }
}
