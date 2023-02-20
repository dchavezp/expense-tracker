package com.devvitt.expensetrackapi.services;

import com.devvitt.expensetrackapi.domain.Transaction;
import com.devvitt.expensetrackapi.exceptions.EtBadRequestException;
import com.devvitt.expensetrackapi.exceptions.EtResourceNotFoundException;
import com.devvitt.expensetrackapi.repositories.TransactionRepository;
import com.devvitt.expensetrackapi.services.interfaces.TransactionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TransactionService implements TransactionServiceI {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Transaction> fetchAllTransaction(Integer userId, Integer categoryId) {
        return transactionRepository.findAll(userId, categoryId);
    }

    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        return transactionRepository.findById(userId, categoryId, transactionId);
    }

    @Override
    public Transaction addTransaction(Integer userId, Integer categoryId, double amount, String note, Long date) throws EtBadRequestException {
        int transactionId = transactionRepository.create(userId, categoryId, amount, note, date);
        return transactionRepository.findById(userId,categoryId,transactionId);
    }

    @Override
    public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException {
        transactionRepository.update(userId, categoryId, transactionId, transaction);
    }

    @Override
    public void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        transactionRepository.removeById(userId, categoryId, transactionId);
    }
}
