package ru.syn.articles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.syn.articles.models.Article;
import ru.syn.articles.services.ArticlesService;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;

    @GetMapping
    public List<Article> getArticles() {
        return articlesService.getAll();
    }

    @PostMapping
    public void createArticle(@RequestBody Article article) {
        articlesService.createArticle(article);
    }

    @PostMapping("/{articleId}")
    public void updateArticle(@RequestBody Article article, @PathVariable long articleId) {
        articlesService.update(article, articleId);
    }

    @PostMapping("/{articleId}/raiting/up")
    public void upVote(@PathVariable long articleId) {
        articlesService.upRaiting(articleId);
    }

    @PostMapping("/{articleId}/raiting/down")
    public void downVote(@PathVariable long articleId) {
        articlesService.downRaiting(articleId);

    }

    @GetMapping("/best")
    public List<Article> getBest(@RequestParam String category) {
        return articlesService.getBest(category);
    }
}
