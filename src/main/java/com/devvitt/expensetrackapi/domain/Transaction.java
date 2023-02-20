package com.devvitt.expensetrackapi.domain;

public class Transaction {
    private Integer transactionId;
    private Integer categoryId;
    private Integer userId;
    private double amount;
    private String note;
    private Long transactionDate;

    public Transaction(Integer transactionId, Integer categoryId, Integer userId, String note, Double amount, Long transactionDate) {
        this.transactionId = transactionId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.note = note;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNote() {
        return note;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }
}
