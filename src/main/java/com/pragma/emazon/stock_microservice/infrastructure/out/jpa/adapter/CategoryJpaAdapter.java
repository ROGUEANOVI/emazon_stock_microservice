package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICategoryPersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageCategoryEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.PageMessages.MINIMUM_PAGE_NUMBER;
import static com.pragma.emazon.stock_microservice.infrastructure.constant.PageMessages.PROPERTY_NAME;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;

    private final EntityMapper entityMapper;

    private final PageCategoryEntityMapper pageCategoryEntityMapper;

    @Override
    @Transactional
    public void createCategory(Category category) {

        categoryRepository.save(entityMapper.toCategoryEntity(category));
    }

    @Override
    @Transactional(readOnly = true)
    public GenericPagination<Category> listCategories(Integer page, Integer size, String direction) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), PROPERTY_NAME);
        Pageable pagination = PageRequest.of(page - MINIMUM_PAGE_NUMBER, size, sort);

        return pageCategoryEntityMapper.toGenericPaginationCategory(categoryRepository.findAll(pagination));
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsCategoryByName(String name) {

        return categoryRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findCategoryById(Long id) {

        return categoryRepository.findById(id).map(entityMapper::toCategory);
    }
}