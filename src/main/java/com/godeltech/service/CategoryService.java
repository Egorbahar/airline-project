package com.godeltech.service;

import com.godeltech.persistence.model.Category;

import java.util.List;

public interface CategoryService {
    Category findById(final Long id);

    Category save(final Category category);

    List<Category> findAll();

    Category update(final Category category);

    void deleteById(final Long id);
}
