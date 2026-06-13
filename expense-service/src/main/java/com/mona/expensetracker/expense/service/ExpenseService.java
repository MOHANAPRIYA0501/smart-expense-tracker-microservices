package com.mona.expensetracker.expense.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mona.expensetracker.expense.dto.ExpenseRequest;
import com.mona.expensetracker.expense.entity.Expense;
import com.mona.expensetracker.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;
    }

    public String addExpense(
            ExpenseRequest request) {

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDescription(request.getDescription());
        expense.setUserEmail(request.getUserEmail());
        expense.setCreatedAt(LocalDateTime.now());

        expenseRepository.save(expense);

        return "Expense added successfully";
    }
    public List<Expense> getAllExpenses() {
    return expenseRepository.findAll();
}
}