package com.scad.expensestracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.scad.expensestracker.models.ExpensesTrackerItem;
import com.scad.expensestracker.repositories.ExpensesTrackerRepository;

@Controller
public class ExpensesTrackerFormController {
    
    @Autowired
    private ExpensesTrackerRepository expensesTrackerRepository;

    @GetMapping("/create-expensestracker")
    public String showCreateForm(ExpensesTrackerItem expensesTrackerItem){
        return "add-ExpensesTracker-item"; // html- file name
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        ExpensesTrackerItem expensesTrackerItem = expensesTrackerRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("ExpensesTrackerItem id: " + id + " not found"));
    
        model.addAttribute("ExpensesTracker", expensesTrackerItem);
        return "update-ExpensesTracker-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpensesTrackerItem(@PathVariable("id") long id, Model model) {
        ExpensesTrackerItem expensesTrackerItem = expensesTrackerRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("ExpensesTrackerItem id: " + id + " not found"));
    
        expensesTrackerRepository.delete(expensesTrackerItem);
        return "redirect:/";
    }

    
}