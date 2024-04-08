package com.scad.expensestracker.repositories;

import com.scad.expensestracker.models.ExpensesTrackerItem;
import org.springframework.data.repository.CrudRepository;

public interface ExpensesTrackerRepository extends CrudRepository<ExpensesTrackerItem, Long> {  
}