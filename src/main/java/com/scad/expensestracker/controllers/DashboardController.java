package com.scad.expensestracker.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.scad.expensestracker.models.ExpensesTrackerItem;
import com.scad.expensestracker.repositories.ExpensesTrackerRepository;

@Controller
public class DashboardController {

    private final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private ExpensesTrackerRepository expensesTrackerRepository;

    private double totalExpense;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        logger.debug("GET request for dashboard");
        totalExpense = calculateTotalExpense();
        logger.info("=========={}",totalExpense);
        double averageDailyExpense = calculateAverageDailyExpense();
        String dominantCategory = findDominantCategory();

        model.addObject("totalExpenses", totalExpense);
        model.addObject("averageDailyExpenses", averageDailyExpense);
        model.addObject("mostExpensiveCategory", dominantCategory);

        return "dashboard"; // Assuming prefix/suffix configuration for views
    }

    private String findDominantCategory() {
        List<ExpensesTrackerItem> items = (List<ExpensesTrackerItem>) expensesTrackerRepository.findAll();

        return items.stream()
            .collect(Collectors.groupingBy(ExpensesTrackerItem::getCategory, Collectors.summingDouble(ExpensesTrackerItem::getPrice)))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    private double calculateTotalExpense() {
         List<ExpensesTrackerItem> expensesTrackerItems = new ArrayList<>();
        expensesTrackerRepository.findAll().forEach(expensesTrackerItems::add);
        return expensesTrackerItems.stream().mapToDouble(ExpensesTrackerItem::getPrice).sum();
    }

    private double calculateAverageDailyExpense() {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());
        return days == 0 ? 0.0 : calculateTotalExpense() / days; // Handle division by zero
    }
}