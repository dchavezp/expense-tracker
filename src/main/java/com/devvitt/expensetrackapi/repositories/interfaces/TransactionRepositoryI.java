package com.devvitt.expensetrackapi.repositories.interfaces;

import com.devvitt.expensetrackapi.domain.Transaction;
import com.devvitt.expensetrackapi.exceptions.EtBadRequestException;
import com.devvitt.expensetrackapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TransactionRepositoryI {
    List<Transaction> findAll(Integer userId, Integer categoryId);
    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
    Integer create(Integer userId, Integer categoryId, double amount, String note, Long date) throws EtBadRequestException;
    void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;
    void removeById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
}
