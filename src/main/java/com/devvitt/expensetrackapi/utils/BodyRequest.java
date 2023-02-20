package com.devvitt.expensetrackapi.utils;

import com.devvitt.expensetrackapi.domain.User;

import java.util.Map;


public interface BodyRequest<T> {
    T getDataFromRequest(Map<String, Object> map);
}
