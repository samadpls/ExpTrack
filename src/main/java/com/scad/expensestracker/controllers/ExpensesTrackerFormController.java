package com.scad.expensestracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.scad.expensestracker.models.ExpensesTrackerItem;
import com.scad.expensestracker.repositories.ExpensesTrackerRepository;

@Controller
public class ExpensesTrackerFormController {

    private final ExpensesTrackerRepository expensesTrackerRepository;

    public ExpensesTrackerFormController(
            ExpensesTrackerRepository expensesTrackerRepository) {
        this.expensesTrackerRepository = expensesTrackerRepository;
    }

    @GetMapping("/create-expensestracker")
    public String showCreateForm(ExpensesTrackerItem expensesTrackerItem) {
        return "add-ExpensesTracker-item";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        ExpensesTrackerItem expensesTrackerItem = findExpenseById(id);

        model.addAttribute("ExpensesTracker", expensesTrackerItem);

        return "update-ExpensesTracker-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpensesTrackerItem(@PathVariable("id") long id) {
        ExpensesTrackerItem expensesTrackerItem = findExpenseById(id);

        expensesTrackerRepository.delete(expensesTrackerItem);

        return "redirect:/";
    }

    /**
     * Retrieves an expense item by its ID.
     *
     * @param id the expense item ID
     * @return the expense item if found
     * @throws IllegalArgumentException if the item does not exist
     */
    private ExpensesTrackerItem findExpenseById(long id) {
        return expensesTrackerRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                String.format("Expense item with id %d not found", id)));
    }
}