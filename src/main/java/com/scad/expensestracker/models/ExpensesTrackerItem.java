package com.scad.expensestracker.models;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "expensestracker_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExpensesTrackerItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 0, message = "Price should be positive")
    private Integer price;

    @Column(name="category")
    private String category;

    private Instant createdDate;
    private Instant modifiedDate;

    public ExpensesTrackerItem(String description, Integer price, String category) {
        this.description = description;
        this.price = price;
        this.category = category;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }
}
