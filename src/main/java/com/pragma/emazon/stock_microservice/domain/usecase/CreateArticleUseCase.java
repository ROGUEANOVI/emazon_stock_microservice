package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.ArticleExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.constant.BrandExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.constant.CategoryExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.*;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.port.api.ICreateArticleServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateArticlePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsArticleByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IFindBrandByIdPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IFindCategoryByIdPersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.ArticleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CreateArticleUseCase implements ICreateArticleServicePort {

    private final ICreateArticlePersistencePort createArticlePersistencePort;
    private final IExistsArticleByNamePersistencePort existsArticleByNamePersistencePort;
    private final IFindBrandByIdPersistencePort findBrandByIdPersistencePort;
    private final IFindCategoryByIdPersistencePort findCategoryByIdPersistencePort;

    public CreateArticleUseCase(
            ICreateArticlePersistencePort createArticlePersistencePort,
            IExistsArticleByNamePersistencePort existsArticleByNamePersistencePort,
            IFindBrandByIdPersistencePort findBrandByIdPersistencePort,
            IFindCategoryByIdPersistencePort findCategoryByIdPersistencePort) {

        this.createArticlePersistencePort = createArticlePersistencePort;
        this.existsArticleByNamePersistencePort = existsArticleByNamePersistencePort;
        this.findBrandByIdPersistencePort = findBrandByIdPersistencePort;
        this.findCategoryByIdPersistencePort = findCategoryByIdPersistencePort;
    }

    @Override
    public void createArticle(Article article) {

        List<Map<String, String>> errors = ArticleValidator.validate(article);

        if (!errors.isEmpty()) {
            throw new ArticleBadRequestException(errors);
        }

        if (Boolean.TRUE.equals(existsArticleByNamePersistencePort.existsArticleByName(article.getName()))) {
            throw new ArticleAlreadyExistsException(ArticleExceptionMessages.ARTICLE_ALREADY_EXISTS, article.getName());
        }

        Brand brand = findBrandByIdPersistencePort.findBrandById(article.getBrand().getId()).orElseThrow(() ->
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

            categories.add(findCategoryByIdPersistencePort.findCategoryById(category.getId()).orElseThrow(() ->
                    new CategoryNotFoundException(CategoryExceptionMessages.CATEGORY_NOT_FOUND, category.getId()))
            );
        }
        article.setCategories(categories);

        createArticlePersistencePort.createArticle(article);
    }
}
