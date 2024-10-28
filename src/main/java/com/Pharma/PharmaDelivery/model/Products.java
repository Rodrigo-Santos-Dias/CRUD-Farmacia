package com.Pharma.PharmaDelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name attribute is mandatory")
    @Size(max = 100, message = "The name attribute must have a maximum of 100 characters")
    private String name;

    private BigDecimal price;

    @Min(value = 0, message = "The stock quantity must be a non-negative value")
    private int stock_quantity;

    private LocalDateTime date;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private User user;
}