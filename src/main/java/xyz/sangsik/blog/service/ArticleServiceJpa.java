package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.sangsik.blog.domain.Article;
import xyz.sangsik.blog.repository.ArticleRepository;

import java.util.List;

/**
 * Created by sangsik on 2017-12-14.
 */
public class ArticleServiceJpa implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article get(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public Article add(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article update(Article article) {
        return null;
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        articleRepository.delete(id);
    }


}




