package com.catalog.admin.domain.category;

import com.catalog.admin.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Pagination<Category> findAll(CategorySearchQuery query);
    Category create(Category category);
    Category update(Category category);
    Optional<Category> findById(CategoryId id);
    void deleteById(CategoryId id);


}
