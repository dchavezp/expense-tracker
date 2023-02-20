package com.devvitt.expensetrackapi.services;

import com.devvitt.expensetrackapi.domain.Category;
import com.devvitt.expensetrackapi.exceptions.EtBadRequestException;
import com.devvitt.expensetrackapi.exceptions.EtResourceNotFoundException;
import com.devvitt.expensetrackapi.repositories.CategoryRepository;
import com.devvitt.expensetrackapi.services.interfaces.CategoryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CategoryService implements CategoryServiceI {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> fetchAllCategories(Integer userId) {
        return categoryRepository.findAll(userId);
    }

    @Override
    public Category fetchCategory(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        return categoryRepository.findById(userId,categoryId);
    }

    @Override
    public Category addCategory(Integer userId, String title, String description) throws EtBadRequestException {
        int categoryId = categoryRepository.create(userId,title,description);
        return categoryRepository.findById(userId,categoryId);
    }

    @Override
    public void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {
        categoryRepository.update(userId, categoryId, category);
    }

    @Override
    public void removeCategoryWillAllTransaction(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        this.fetchCategory(userId,categoryId);
        categoryRepository.removeById(userId,categoryId);
    }
}
