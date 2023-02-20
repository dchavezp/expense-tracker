package com.devvitt.expensetrackapi.repositories.interfaces;

import com.devvitt.expensetrackapi.domain.Category;
import com.devvitt.expensetrackapi.exceptions.EtBadRequestException;
import com.devvitt.expensetrackapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CategoryRepositoryI {
    List<Category> findAll(Integer userId) throws EtResourceNotFoundException;
    Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;
    Integer create(Integer userId, String title, String description) throws EtBadRequestException;
    void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;
    void removeById(Integer userId, Integer categoryId);
}
