package ru.syn.articles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.syn.articles.models.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByCategory(String category);
}
