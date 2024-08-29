package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.ArticleExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.constant.BrandExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.constant.CategoryExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.*;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.api.IArticleServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IArticlePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IBrandPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.ArticleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.pragma.emazon.stock_microservice.domain.validation.ArticleSort.resolveSortBy;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final IBrandPersistencePort brandPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort, IBrandPersistencePort brandPersistencePort, ICategoryPersistencePort categoryPersistencePort) {

        this.articlePersistencePort = articlePersistencePort;
        this.brandPersistencePort = brandPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void createArticle(Article article) {

        List<Map<String, String>> errors = ArticleValidator.validate(article);

        if (!errors.isEmpty()) {
            throw new ArticleBadRequestException(errors);
        }

        if (Boolean.TRUE.equals(articlePersistencePort.existsArticleByName(article.getName()))) {
            throw new ArticleAlreadyExistsException(ArticleExceptionMessages.ARTICLE_ALREADY_EXISTS, article.getName());
        }

        Brand brand = brandPersistencePort.findBrandById(article.getBrand().getId()).orElseThrow(() ->
                new BrandNotFoundException(BrandExceptionMessages.BRAND_NOT_FOUND, article.getBrand().getId())
        );
        article.setBrand(brand);

        List<Long> categoryIds = new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        for (Category category : article.getCategories()) {
            if (categoryIds.contains(category.getId())) {
                throw new DuplicateCategoryException(CategoryExceptionMessages.DUPLICATE_CATEGORY, category.getId());
            }
            categoryIds.add(category.getId());

            categories.add(categoryPersistencePort.findCategoryById(category.getId()).orElseThrow(() ->
                    new CategoryNotFoundException(CategoryExceptionMessages.CATEGORY_NOT_FOUND, category.getId()))
            );
        }
        article.setCategories(categories);

        articlePersistencePort.createArticle(article);
    }

    @Override
    public GenericPagination<Article> listArticles(Integer page, Integer size, String direction, String sortBy) {

        GenericPagination<Article> listArticles = articlePersistencePort.listArticles(page, size, direction, resolveSortBy(sortBy));

        if (listArticles.getTotalElements() == 0) {
            throw new NoDataFoundArticleException(ArticleExceptionMessages.NO_DATA_FOUND_ARTICLE);
        }

        return listArticles;
    }
}