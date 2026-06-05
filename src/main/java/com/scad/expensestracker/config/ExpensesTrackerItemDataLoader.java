package com.scad.expensestracker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scad.expensestracker.models.ExpensesTrackerItem;
import com.scad.expensestracker.repositories.ExpensesTrackerRepository;

/**
 * Loads initial sample expense records when the application starts.
 * Seed data is inserted only if the database is empty.
 */
@Component
public class ExpensesTrackerItemDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ExpensesTrackerItemDataLoader.class);

    @Autowired
    ExpensesTrackerRepository expensesTrackerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    /**
     * Inserts sample data into the database if no records exist.
     */
    private void loadSeedData() {

        // Prevent duplicate seed data insertion
        if (expensesTrackerRepository.count() == 0) {

            ExpensesTrackerItem milkExpense =
                    new ExpensesTrackerItem("milk", 50, "grocery");

            ExpensesTrackerItem leavesExpense =
                    new ExpensesTrackerItem("leaves", 20, "grocery");

            expensesTrackerRepository.save(milkExpense);
            expensesTrackerRepository.save(leavesExpense);
        }

        logger.info("Number of ExpensesTrackerItems: {}",
                expensesTrackerRepository.count());
    }
}