package com.devvitt.expensetrackapi.resources;

import com.devvitt.expensetrackapi.domain.Transaction;
import com.devvitt.expensetrackapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {
    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId){
        int userId = (Integer) request.getAttribute("userId");
        List<Transaction> transactions = transactionService.fetchAllTransaction(userId,categoryId);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }
    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> createTransaction(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId, @RequestBody Map<String, Object> bodyData){
        int userId=(Integer) request.getAttribute("userId");
        double amount = Double.parseDouble(bodyData.get("amount").toString());
        String note = (String) bodyData.get("note");
        Long transactionDate = (Long) bodyData.get("transactionDate");
        Transaction transaction = transactionService.addTransaction(userId,categoryId,amount,note,transactionDate);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,@PathVariable("categoryId") Integer categoryId, @PathVariable("transactionId") Integer transactionId){
        int userId=(Integer) request.getAttribute("userId");
        Transaction transaction = transactionService.fetchTransactionById(userId,categoryId,transactionId);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }
    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String,String>> updateTransaction(HttpServletRequest request,@PathVariable("categoryId") Integer categoryId, @PathVariable("transactionId") Integer transactionId,@RequestBody Transaction bodyData){
        int userId = (Integer) request.getAttribute("userId");
        transactionService.updateTransaction(userId,categoryId,transactionId,bodyData);
        Map<String, String> response = new HashMap<>();
        response.put("message","Transaction updated");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String,String>> deleteTransaction(HttpServletRequest request,@PathVariable("categoryId") Integer categoryId, @PathVariable("transactionId") Integer transactionId,@RequestBody Transaction bodyData){
        int userId = (Integer) request.getAttribute("userId");
        transactionService.removeTransaction(userId,categoryId,transactionId);
        Map<String, String> response = new HashMap<>();
        response.put("message","Transaction removed");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
