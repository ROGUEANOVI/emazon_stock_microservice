package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListCategoriesPersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.constant.PropertyNames;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageCategoryEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class ListCategoriesJpaAdapter implements IListCategoriesPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final PageCategoryEntityMapper pageCategoryEntityMapper;

    @Override
    public GenericPagination<Category> listCategories(Integer page, Integer size, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), PropertyNames.NAME);
        Pageable pagination = PageRequest.of(page - 1, size, sort);

        return pageCategoryEntityMapper.toGenericPaginationCategory(categoryRepository.findAll(pagination));
    }
}
