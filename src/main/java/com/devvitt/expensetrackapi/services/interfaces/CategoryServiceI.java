package com.devvitt.expensetrackapi.services.interfaces;

import com.devvitt.expensetrackapi.domain.Category;
import com.devvitt.expensetrackapi.exceptions.EtBadRequestException;
import com.devvitt.expensetrackapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CategoryServiceI {
    List<Category> fetchAllCategories(Integer id);
    Category fetchCategory(Integer userId, Integer categoryId) throws EtResourceNotFoundException;
    Category addCategory(Integer userId, String title, String description) throws EtBadRequestException;
    void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;
    void removeCategoryWillAllTransaction(Integer userId, Integer categoryId) throws EtResourceNotFoundException;
}
