# Небольшое учебное (тестовое) задание на Spring

## Суть задания
Создать небольшое тестовое приложение, которое может:
- выводить список статей из БД
- добавлять статьи в БД
- редактировать статьи в БД
- Также нужно добавить тесты

## Зависимости
- [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/3.2.2)
- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/3.2.2)
- [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test/3.2.2)
- [H2 Database Engine](https://mvnrepository.com/artifact/com.h2database/h2/2.2.224)
- [Project Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30)

## Сущности
### Article
Сущность статья, имеет поля:
- id (long) - id статьи
- title (String) - заголовок статьи
- text (String) - текст статьи
- description (String) - описание статьи
- category (String) - категория статьи
- raiting (int) - рейтинг статьи

## Пакеты
### controllers
Здесь хранится ArticlesController для обработки http-запросов
Основные методы:
- List<Article> getArticles() - выводит все статьи из БД
- void createArticle(@RequestBody Article article) - создает и записывает статью в БД
- void updateArticle(@RequestBody Article article, @PathVariable long articleId) - редактирует Id статьи
- void upVote(@PathVariable long articleId) - увеличивает рейтинг выбранной статьи
- void downVote(@PathVariable long articleId) - уменьшает рейтинг выбранной статьи
- List<Article> getBest(@RequestParam String category) - выводит список лучших статей по выбранной категории в порядке убывания рейтинга
### exceptions
Здесь хранится ArticleNotFoundException для случаев, когда в БД не найдена статья
### models
Здесь хранится сущность Article
### repository
Пакет для хранения ArticleRepository
### services
Тут храним сервис ArticlesService для связи контроллера и репозитория.

Также в \articles\src\test хранится ArticlesControllerTest - который тестирует метод getBest
