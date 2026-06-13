package com.mona.expensetracker.expense.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mona.expensetracker.expense.dto.ExpenseRequest;
import com.mona.expensetracker.expense.entity.Expense;
import com.mona.expensetracker.expense.service.ExpenseService;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(
            ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @PostMapping
    public String addExpense(
            @RequestBody ExpenseRequest request) {

        return expenseService.addExpense(request);
    }
    @GetMapping
public List<Expense> getAllExpenses() {
    return expenseService.getAllExpenses();
}
@GetMapping("/{id}")
public Expense getExpenseById(@PathVariable Long id)
{
    return expenseService.getExpenseById(id);
}
@PutMapping("/{id}")
public String updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest request) {
    return expenseService.updateExpense(id, request);
}
    }