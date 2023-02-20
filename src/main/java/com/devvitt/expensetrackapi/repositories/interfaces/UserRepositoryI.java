package com.devvitt.expensetrackapi.repositories.interfaces;

import com.devvitt.expensetrackapi.domain.User;
import com.devvitt.expensetrackapi.exceptions.EtAuthException;

import java.util.List;

public interface UserRepositoryI {
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;
    User findByEmailAndPassword(String email, String password) throws EtAuthException;
    Integer getCountByEmail(String email);
    User findById(Integer userId);
    List<User> getListOfUsers();
}
