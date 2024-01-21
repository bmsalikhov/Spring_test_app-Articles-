package ru.syn.articles.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.syn.articles.exceptions.ArticleNotFoundException;
import ru.syn.articles.models.Article;
import ru.syn.articles.repository.ArticleRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class ArticlesService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public void createArticle(Article article) {
        article.setId(new Random().nextLong());
        articleRepository.save(article);
    }

    public void update(Article article, long id) {
        article.setId(id);
        articleRepository.save(article);
    }

    public void upRaiting(long id) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        int newRate = article.getRating() + 1;
        article.setRating(newRate);
        articleRepository.save(article);
    }

    public void downRaiting(long id) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        int newRate = article.getRating() - 1;
        article.setRating(newRate);
        articleRepository.save(article);
    }

    public List<Article> getBest(String category) {
        return articleRepository.findAllByCategory(category)
                .stream()
                .filter(article -> article.getRating() > 0)
                .sorted(Comparator.comparing(Article::getRating).reversed())
                .toList();
    }
}
