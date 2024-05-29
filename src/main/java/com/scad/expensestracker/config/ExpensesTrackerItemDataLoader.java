package com.scad.expensestracker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scad.expensestracker.models.ExpensesTrackerItem;
import com.scad.expensestracker.repositories.ExpensesTrackerRepository;

@Component
public class ExpensesTrackerItemDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ExpensesTrackerItemDataLoader.class);

    @Autowired
    ExpensesTrackerRepository ExpensesTrackerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (ExpensesTrackerRepository.count() == 0) {
            ExpensesTrackerItem ExpensesTrackerItem1 = new ExpensesTrackerItem("milk", 50,"grocery");
            ExpensesTrackerItem ExpensesTrackerItem2 = new ExpensesTrackerItem("leaves",20,"grocery");

            ExpensesTrackerRepository.save(ExpensesTrackerItem1);
            ExpensesTrackerRepository.save(ExpensesTrackerItem2); 
        }

        logger.info("Number of ExpensesTrackerItems: {}", ExpensesTrackerRepository.count());
    }
    
}