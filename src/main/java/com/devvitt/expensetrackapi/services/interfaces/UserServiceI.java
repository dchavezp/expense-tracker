package com.devvitt.expensetrackapi.services.interfaces;

import com.devvitt.expensetrackapi.domain.User;
import com.devvitt.expensetrackapi.exceptions.EtAuthException;

import java.util.List;

public interface UserServiceI {

    User validateUser(String email, String password) throws EtAuthException;
    User registerUser(User user) throws EtAuthException;
    List<User> listOfUsers();
}
