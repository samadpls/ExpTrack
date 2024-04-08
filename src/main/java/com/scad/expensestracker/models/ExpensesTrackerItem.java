package com.scad.expensestracker.models;

import java.time.Instant;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "expensestracker_items")
public class ExpensesTrackerItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Description is required")
    private String description;

    @Getter
    @Setter
    private Integer price;

    @Getter
    @Setter
    private Instant createdDate;

    @Getter
    @Setter
    private Instant modifiedDate;

    public ExpensesTrackerItem() {}

    public ExpensesTrackerItem(String description, Integer price) {
        this.description = description;
        this.price = price;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }
    
    @Override
    public String toString() {
        return String.format("ExpensesTrackerItem{id=%d, description='%s', price='%d', createdDate='%s', modifiedDate='%s'}",
        id, description, price, createdDate, modifiedDate);
    }
}
