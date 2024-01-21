package ru.syn.articles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import ru.syn.articles.models.Article;
import ru.syn.articles.repository.ArticleRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticlesControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void getBestTest() throws URISyntaxException {
        articleRepository.deleteAll();

        Article article1 = new Article(10, "a1", "a1 desc", "test_cat", -2);
        Article article2 = new Article(11, "a2", "a2 desc", "test_cat", 10);
        Article article3 = new Article(12, "a3", "a3 desc", "test_cat", 15);
        Article article4 = new Article(13, "a4", "a4 desc", "test_cat", 0);
        Article article5 = new Article(14, "a5", "a5 desc", "test_another_cat", 15);

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);
        articleRepository.save(article4);
        articleRepository.save(article5);

        // when

        ResponseEntity<List<Article>> responseEntity = restTemplate.exchange(
                new RequestEntity<>(
                        HttpMethod.GET,
                        new URI("/articles/best?category=test_cat")),
                new ParameterizedTypeReference<>() {}
        );


        // then
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Article> response = responseEntity.getBody();
        assert response != null;
        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals("a2", response.get(1).getTitle());
        Assertions.assertEquals("a3", response.get(0).getTitle());
    }

}
