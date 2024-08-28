package com.pragma.emazon.stock_microservice.domain.model;

import java.util.List;

public class GenericPagination<T> {

    private final List<T> content;
    private final Integer pageNumber;
    private final Integer pageSize;
    private final Long totalElements;
    private final Integer totalPages;
    private final Boolean firstPage;
    private final Boolean lastPage;

    public GenericPagination(List<T> content, Integer pageNumber, Integer pageSize, Long totalElements, Integer totalPages, Boolean firstPage, Boolean lastPage) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
    }

    // Getters and Setters

    public List<T> getContent() {
        return content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Boolean isFirstPage() {
        return firstPage;
    }

    public Boolean isFirstPage(boolean firstPage) {
        return firstPage;
    }

    public Boolean isLastPage() {
        return lastPage;
    }
}
