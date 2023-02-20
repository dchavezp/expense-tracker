package com.devvitt.expensetrackapi.utils;

import com.devvitt.expensetrackapi.domain.User;

import java.util.Map;

public class UserAdapter implements BodyRequest<User> {
    @Override
    public User getDataFromRequest(Map<String, Object> map) {
        String firstName = (String) map.get("firstName");
        String lastName = (String) map.get("lastName");
        String email = (String) map.get("email");
        String password = (String) map.get("password");
        return new User(0,firstName,lastName,email,password);
    }
}
