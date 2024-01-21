package ru.syn.articles.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Article {
    @Id
    private long id;
    private String title;
    private String description;
    private String category;
    private int rating;


    public Article() {

    }
}

