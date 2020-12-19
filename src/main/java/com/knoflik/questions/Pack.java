package com.knoflik.questions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "packs")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String packageName;
    private String authorsName;

    @OneToMany
    @JoinColumn(name = "themes")
    private List<Theme> themes = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(final List<Theme> themes) {
        this.themes = themes;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(final String authorsName) {
        this.authorsName = authorsName;
    }

    public void addTheme(final Theme theme) {
        themes.add(theme);
    }
}
