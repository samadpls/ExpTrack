package com.scad.expensestracker;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.scad.expensestracker.controllers.ExpensesTrackerFormController;
import com.scad.expensestracker.models.ExpensesTrackerItem;
import com.scad.expensestracker.repositories.ExpensesTrackerRepository;


class ExpensesTrackerFormControllerTest {

    @InjectMocks
    private ExpensesTrackerFormController expensesTrackerFormController;

    @Mock
    private ExpensesTrackerRepository expensesTrackerRepository;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowUpdateForm() {
        long id = 1L;
        ExpensesTrackerItem mockItem = new ExpensesTrackerItem();
        when(expensesTrackerRepository.findById(id)).thenReturn(Optional.of(mockItem));

        String viewName = expensesTrackerFormController.showUpdateForm(id, model);

        assertEquals("update-ExpensesTracker-item", viewName);

        verify(model).addAttribute(eq("ExpensesTracker"), same(mockItem));
    }

}