package com.scad.expensestracker.controllers;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scad.expensestracker.models.ExpensesTrackerItem;
import com.scad.expensestracker.repositories.ExpensesTrackerRepository;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
public class ExpensesTrackerItemController {
    private final Logger logger = LoggerFactory.getLogger(ExpensesTrackerItemController.class);
   
    @Autowired
    private ExpensesTrackerRepository ExpensesTrackerRepository;

    @GetMapping("/")
    public ModelAndView index(Model model){
        logger.debug("root to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        List<ExpensesTrackerItem> expensesTrackerItems = new ArrayList<>();
        ExpensesTrackerRepository.findAll().forEach(expensesTrackerItems::add);
        double totalPrice = expensesTrackerItems.stream().mapToDouble(ExpensesTrackerItem::getPrice).sum();
        modelAndView.addObject("ExpensesTrackerItems", expensesTrackerItems);
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        modelAndView.addObject("totalPrice", totalPrice);


        return modelAndView;
    }
    
    @PostMapping("/ExpensesTracker")
    public String createExpensesTrackerItem(@Valid ExpensesTrackerItem expensesTrackerItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-ExpensesTracker-item";
        }
        expensesTrackerItem.setCreatedDate(Instant.now());
        expensesTrackerItem.setModifiedDate(Instant.now());
        ExpensesTrackerRepository.save(expensesTrackerItem);
        return "redirect:/";
    }

    @PostMapping("/ExpensesTracker/{id}")
    public String updateExpensesTrackerItem(@PathVariable("id") long id, @Valid ExpensesTrackerItem expensesTrackerItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            expensesTrackerItem.setId(id);
            return "update-ExpensesTracker-item";
        }

        expensesTrackerItem.setModifiedDate(Instant.now());
        ExpensesTrackerRepository.save(expensesTrackerItem);
        return "redirect:/";
    }


}