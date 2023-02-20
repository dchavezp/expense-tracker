package com.devvitt.expensetrackapi.services.interfaces;

import com.devvitt.expensetrackapi.domain.Transaction;
import com.devvitt.expensetrackapi.exceptions.EtBadRequestException;
import com.devvitt.expensetrackapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TransactionServiceI {
    List<Transaction> fetchAllTransaction(Integer userId, Integer categoryId);
    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
    Transaction addTransaction(Integer userId, Integer categoryId, double amount, String note, Long date) throws EtBadRequestException;
    void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;
    void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
}
